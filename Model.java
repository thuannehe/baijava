import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;
public class Model {
    private List<String> data = new ArrayList<>();

    public void addData(String item) {
        data.add(item);
    }

    public List<String> getData() {
        return data;
    }
}
class View {
    public void displayData(List<String> data) {
        System.out.println("Data:");
        data.forEach(System.out::println);
    }
}

// Controller
class Controller {
    private Model model;
    private View view;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    public void addData(String item) {
        model.addData(item);
    }

    public void displayData() {
        List<String> data = model.getData();
        view.displayData(data);
    }
}

 class Main {
    public static void main(String[] args) throws IOException {

        Model model = new Model();
        View view = new View();
        Controller controller = new Controller(model, view);


        controller.addData("Item 1");
        controller.addData("Item 2");
        controller.addData("Item 3");


        controller.displayData();


        saveDataToFile(model.getData(), "data.txt");


        List<String> loadedData = loadDataFromFile("data.txt");
        System.out.println("Loaded data:");
        loadedData.forEach(System.out::println);


        String folderPath = "path/to/folder";
        List<Path> allFiles = getAllFilesInFolder(folderPath);
        System.out.println("All files in folder:");
        allFiles.forEach(System.out::println);


        Button button = new Button();
        button.setOnClickListener(() -> System.out.println("Button clicked!"));
        button.click();
    }

    public static void saveDataToFile(List<String> data, String filePath) throws IOException {
        try (PrintWriter writer = new PrintWriter(filePath)) {
            data.forEach(writer::println);
        }
    }

    public static List<String> loadDataFromFile(String filePath) throws IOException {
        try (Stream<String> lines = Files.lines(Paths.get(filePath))) {
            return lines.collect(Collectors.toList());
        }
    }

    public static List<Path> getAllFilesInFolder(String folderPath) throws IOException {
        try (Stream<Path> fileStream = Files.walk(Paths.get(folderPath))) {
            return fileStream.filter(Files::isRegularFile).collect(Collectors.toList());
        }
    }

    interface OnClickListener {
        void onClick();
    }

    static class Button {
        private OnClickListener onClickListener;

        public void setOnClickListener(OnClickListener onClickListener) {
            this.onClickListener = onClickListener;
        }

        public void click() {
            if (onClickListener != null) {
                onClickListener.onClick();
            }
        }
    }
}
