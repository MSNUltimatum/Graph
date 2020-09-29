package abstractClasses;

import interfaces.IGraph;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public abstract class Graph<T> implements IGraph<T> {
    protected HashMap<T, HashMap<T, Double>> graph = new HashMap<>();

    Graph(){
    }

    Graph(Graph<T> g){
        for (T node: g.graph.keySet()
             ) {
            graph.put(node, new HashMap<>(g.graph.get(node)));
        }
    }

    Graph(String fileName, Class<T> tClass) throws FileNotFoundException {
        try(FileReader reader = new FileReader(fileName)) {
            fillGraphFromFile(reader, tClass);
        } catch (IOException e) {
            throw new FileNotFoundException("No such file");
        }
    }

    private void fillGraphFromFile(FileReader reader, Class<T> tClass) {
        Scanner in = new Scanner(reader);
        List<String[]> lines = new ArrayList<>();
        readLines(in, lines);
        lines.forEach(line -> addVertex(castArg(tClass, line[0])));
        if(lines.get(0)[1].contains("=")){
            fillWeightedGraph(lines, tClass);
        } else {
            fillNoWeightedGraph(lines, tClass);
        }
    }

    private void fillNoWeightedGraph(List<String[]> lines, Class<T> tClass) {
        lines.forEach(line ->{
            for (int i = 1; i < line.length; i++) {
                addEdge(castArg(tClass, line[0]), castArg(tClass, line[i]));
            }
        });
    }

    private void fillWeightedGraph(List<String[]> lines, Class<T> tClass) {
        lines.forEach(line ->{
            for (int i = 1; i < line.length; i++) {
                addEdge(castArg(tClass, line[0]), castArg(tClass, line[i].split("=")[0]), Integer.parseInt(line[i].split("=")[1]));
            }
        });
    }

    private void readLines(Scanner in, List<String[]> lines) {
        while (in.hasNextLine()){
            lines.add(in.nextLine().split(" "));
        }
    }

    private T castArg(Class<T> tClass, String line) {
        if (tClass.isAssignableFrom(String.class)) {
            return (T) line;
        } else if (tClass.isAssignableFrom(Integer.class)) {
            return (T) Integer.valueOf(line);
        } else if (tClass.isAssignableFrom(Boolean.class)) {
            return (T) Boolean.valueOf(line);
        } else if (tClass.isAssignableFrom(Double.class)) {
            return (T) Double.valueOf(line);
        } else {
            throw new IllegalArgumentException("Bad type.");
        }
    }

    @Override
    public void addVertex(T name) {
        if(!hasVertex(name)) {
            graph.put(name, new HashMap<>());
        } else {
            throw new RuntimeException("Existed vertex " + name);
        }
    }

    @Override
    public boolean hasVertex(T name) {
        return graph.containsKey(name);
    }

    @Override
    public void addEdge(T fromV, T toV){
    }

    @Override
    public void addEdge(T fromV, T toV, double weight) {
    }

    @Override
    public boolean hasEdge(T from, T to) {
        if(hasVertex(from) && hasVertex(to))
            return graph.get(from).containsKey(to);
        return false;
    }

    @Override
    public void deleteVertex(T name) {
        if(hasVertex(name) && graph.get(name).isEmpty()) {
            graph.remove(name);
        }
    }

    @Override
    public void printGraphToFile(String fileName) {
        try(FileWriter fileWriter = new FileWriter(fileName.trim())) {
            for (T node : graph.keySet()) {
                printKeyAndAdjectiveVertexes(fileWriter, node);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void printKeyAndAdjectiveVertexes(FileWriter writer, T node) throws IOException {
        printKey(writer, node);
        if(graph.get(node) != null && !graph.get(node).isEmpty()){
            printAdjectiveNodes(writer, node);
        } else {
            printAboutNoAdjectiveVertexes(writer);
        }
    }

    private void printKey(FileWriter writer, T node) throws IOException {
        writer.write(String.format("Вершина %s. ", node));
    }

    private void printAdjectiveNodes(FileWriter writer, T node) throws IOException {
        writer.write("Смежные вершины:\n");
        for (T adjNode: graph.get(node).keySet()
             ) {
            Double nodeWeight = graph.get(node).get(adjNode);
            writer.write(String.format("    Вершина %s с весом %s\n", adjNode, nodeWeight == null ? "INF" : nodeWeight));
        }
    }

    private void printAboutNoAdjectiveVertexes(FileWriter writer) throws IOException {
        writer.write("Нет смежных вершин\n");
    }

    public void printGraphToConsole(){
        for (T node : graph.keySet()) {
            printKeyAndAdjectiveVertexesToConsole(node);
        }
    }

    private void printKeyAndAdjectiveVertexesToConsole(T node) {
        System.out.printf("Вешина %s. %n", node);
        if(graph.get(node) != null && !graph.get(node).isEmpty()){
            printAdjectiveNodesToConsole(node);
        } else {
            System.out.println("Нет смежных вершин\n");
        }
    }

    private void printAdjectiveNodesToConsole(T node) {
        System.out.println("Смежные вершины:\n");
        for (T adjNode: graph.get(node).keySet()
        ) {
            Double nodeWeight = graph.get(node).get(adjNode);
            System.out.printf("    Вершина %s с весом %s\n%n", adjNode, nodeWeight == null ? "INF" : nodeWeight);
        }
    }

    public HashMap<T, HashMap<T, Double>> getGraph() {
        return graph;
    }

    @Override
    public double getWeightFromEdge(T fromV, T toV) {
        return graph.get(fromV).get(toV);
    }

    @Override
    public void setWeightToEdge(T fromV, T toV, double weight) {
        graph.get(fromV).put(toV, weight);
    }

    @Override
    public int getVertexesCount() {
        return graph.size();
    }

    @Override
    public boolean isConnected() {
        Set<T> valsDFS = new HashSet<>();
        Optional<T> s = graph.keySet().stream().findFirst();
        if(s.isPresent()) {
            DFS(s.get(), valsDFS);
            return valsDFS.equals(graph.keySet());
        } else {
            return false;
        }
    }

    @Override
    public int countReachableNodes(T u){
        Set<T> valsDFS = new HashSet<>();
        DFS(u, valsDFS);
        return valsDFS.size();
    }

    public boolean reachable(T s, T d){
        Set<T> valsDFS = new HashSet<>();
        DFS(s, valsDFS);
        return valsDFS.contains(d);
    }

    private void DFS(T v, Set<T> used){
        used.add(v);
        graph.keySet().forEach(e -> {
            if(!used.contains(e) && graph.get(v).containsKey(e)){
                DFS(e, used);
            }
        });
    }
}
