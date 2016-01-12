package net.praseodym.activelearner;

import java.nio.file.Files;
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
            if (matcher.matches()) {
                newEdge = matcher.group(1);
                String newLabel = matcher.group(2);

                newLabel = Arrays.stream(newLabel.split(" / ")).map(SimplifyDot::convertToSymbolic)
                        .collect(Collectors.joining(" / "));

                // TODO: more intelligent label merging
//                String prefix = Strings.commonPrefix(label, newLabel);

                label += (!label.isEmpty() ? " | " : "") + newLabel;

            } else {
                newEdge = "";
            }

            if (!oldEdge.isEmpty() && !oldEdge.equals(newEdge)) {
                output.add("\t" + oldEdge + " [label=\"" + label.trim() + "\"];");
                label = "";
            }

            if (!matcher.matches()) {
                output.add(line);
            }
            oldEdge = newEdge;
        }

        return output;
    }


    public static void main(String[] args) throws Exception {
        String path = "learnedModel";
        List<String> lines = Files.readAllLines(Paths.get(path + ".dot"));
        List<String> simpified = simplifyDot(lines);
        //System.out.println(simpified);
        simpified.stream().forEach(a -> System.out.println(a + "\n"));
        //Files.write(Paths.get(path + "_simple.dot"), simpified, Charset.defaultCharset());
    }

    public static char calculateFromAlphabetIndex(int index) {
        return alphabet.charAt(index - 1);
    }

    public static String calculateFromAlphabetIndex(String label) {
        return String.valueOf(calculateFromAlphabetIndex(Integer.valueOf(label)));
    }

    private static String convertToSymbolic(String label) {
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
