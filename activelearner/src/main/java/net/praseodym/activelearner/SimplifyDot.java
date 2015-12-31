package net.praseodym.activelearner;

import com.google.common.base.Strings;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Simplify Graphviz dot file
 */
public class SimplifyDot {
    public final static Pattern p = Pattern.compile("^\\s*(\\w+ -> \\w+) \\[label=\\\"(.+)\\\"\\];$");

    public static void main(String[] args) throws Exception {
        String path = "learnedModel";
        List<String> lines = Files.readAllLines(Paths.get(path + ".dot"));
        List<String> simpified = simplifyDot(lines);
        //System.out.println(simpified);
        simpified.stream().forEach(a -> System.out.println(a + "\n"));
        //Files.write(Paths.get(path + "_simple.dot"), simpified, Charset.defaultCharset());
    }

    public static List<String> simplifyDot(List<String> lines) {
        List<String> output = new ArrayList<>(lines.size());
        String oldEdge = "", newEdge, label = "";
        for (String line : lines) {
            Matcher matcher = p.matcher(line);
            if (matcher.matches()) {
                newEdge = matcher.group(1);
                // TODO: more intelligent label merging
                String newLabel = matcher.group(2);
                String prefix = Strings.commonPrefix(label, newLabel);

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

}
