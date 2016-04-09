package net.praseodym.activelearner;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Simplify Graphviz dot file
 */
public class SimplifyDot {
    final static Pattern p = Pattern.compile("^\\s*(\\w+ -> \\w+) \\[label=\\\"(.+)\\\"\\];$");
    final static Pattern labelPattern = Pattern.compile("^\\d+ / \\d+$");
    final static String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static List<String> simplifyDot(List<String> lines) {
        List<String> output = new ArrayList<>(lines.size());
        String oldEdge = "", newEdge, label = "";
        for (String line : lines) {
            Matcher matcher = p.matcher(line);
            String newLabel;
            if (matcher.matches()) {
                newEdge = matcher.group(1);
                newLabel = matcher.group(2);

                newLabel = Arrays.stream(newLabel.split(" / ")).map(SimplifyDot::convertLabelToSymbol)
                        .collect(Collectors.joining(" / "));
            } else {
                newEdge = "";
                newLabel = "";
            }

            if (oldEdge.isEmpty() || oldEdge.equals(newEdge)) {
                // TODO: more intelligent label merging (Strings.commonPrefix?)
                label += (!label.isEmpty() ? " | " : "") + newLabel;
            } else {
                output.add("\t" + oldEdge + " [label=\"" + label.trim() + "\"];");
                label = newLabel;
            }

            if (!matcher.matches()) {
                output.add(line);
            }
            oldEdge = newEdge;
        }

        return output;
    }

    public static void simplifyDot(Path finalModel, Path destination) {
        try {
            List<String> lines = Files.readAllLines(finalModel).stream()
                    .filter(s -> !s.contains("invalid_state")).collect(Collectors.toList());
            List<String> simplified = SimplifyDot.simplifyDot(lines);
            Files.write(destination, simplified, Charset.defaultCharset());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) throws Exception {
        String path = "learnedModel";
        List<String> lines = Files.readAllLines(Paths.get(path + ".dot"));
        List<String> simpified = simplifyDot(lines);
        //System.out.println(simpified);
        simpified.stream().forEach(System.out::println);
        Files.write(Paths.get(path + "_simple.dot"), simpified, Charset.defaultCharset());
    }

    public static char calculateFromAlphabetIndex(int index) {
        return alphabet.charAt(index - 1);
    }

    public static String calculateFromAlphabetIndex(String label) {
        return String.valueOf(calculateFromAlphabetIndex(Integer.valueOf(label)));
    }

    private static String convertLabelToSymbol(String label) {
        try {
            return calculateFromAlphabetIndex(label);
        } catch (NumberFormatException e) {
            if (label.contains("_assert")) {
                int index = label.indexOf("_assert");
                String prefix = label.substring(0, index);
                String suffix = label.substring(index);
                return calculateFromAlphabetIndex(prefix) + suffix;
            } else {
                return label;
            }
        }
    }
}
