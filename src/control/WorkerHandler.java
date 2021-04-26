package control;

import model.BinarySearchTree;
import model.Worker;

public class WorkerHandler {

    private BinarySearchTree<Worker> allWorker;

    public WorkerHandler(){
        allWorker = new BinarySearchTree<>();
    }

    /**
     * Diese Methode fügt einen Auftrag mit entsprechender id zum entsprechenden Arbeiter in die Warteschlange hinzu.
     * Falls der Arbeiter nicht existiert, so wird er zunächst erstellt und dem Baum hinzugefügt.
     * @param name
     * @param id
     */
    public void addTaskAndWorker(String name, int id){
        //TODO 03: Setzen Sie die Methode gemäß obiger Beschreibung um.
        Worker worker = allWorker.search(new Worker(name));
        if(worker!=null){
            worker.addTask(id);
        }else{
            worker = new Worker(name);
            worker.addTask(id);
            allWorker.insert(worker);
        }
    }

    /**
     * Diese Methode entfernt alle Arbeitsaufträge aus dem Baum.
     * Dabei werden alle Arbeitsaufträge sortiert nach ihrem Arbeiter als großer, vollständiger String in der Systemkonsole ausgegeben.
     * Nach Aufruf dieser Methode befinden sich alle Arbeiter immernoch im Baum, jedoch hat keiner mehr einen Arbeitsauftrag.
     */
    public void releaseAllTasksAndShowWorker(){
        System.out.println(releaseAllTasksAndShowWorker(allWorker));
    }

    /**
     * Rekursive Methode, die am Ende einen String liefert. Dieser hat folgenden Aufbau: 1.Name:1.id-2.id-3.id#2.Name:1.id-2.id#3.Name:1.id etc.
     * Die Namen sind alphabetisch sortiert.
     * Nach Aufruf dieser Methode befindet sich kein Auftrag mehr im Baum. Die Arbeiter werden nicht gelöscht.
     * @param tree
     * @return String bestehend aus Arbeiternamen und deren IDs.
     */
    private String releaseAllTasksAndShowWorker(BinarySearchTree<Worker> tree){
        //TODO 04a: Stellen Sie handschriftlich die gewünschte Ausgabe gemäß des vorhanden Baums dar (siehe MainController ab Zeile 13). Hierbei genügen die ersten drei Arbeiter und ihre IDs, die von dieser Methode ausgegeben werden.
        //TODO 04b: Setzen Sie anschließend diese Methode gemäß obiger Beschreibung um.
        String o = "";

        if(!tree.isEmpty()) {
            o += showWorkers(tree.getLeftTree());

            o += "Worker: "+tree.getContent().getName()+", Tasks: ";
            if(tree.getContent().getTasks() != null && tree.getContent().getTasks().length>0) {
                o += "{";
                for (int i = 0; i < tree.getContent().getTasks().length; i++) {
                    o += tree.getContent().getTasks()[i].getID() + ";";
                }
                o += "}\n";
            }else{
                o += "No Tasks active.\n";
            }
            tree.getContent().completeAllTasks();

            o += showWorkers(tree.getRightTree());
        }
        return o;
    }

    public String showWorkers(BinarySearchTree<Worker> allWorkers){
        String o = "";
        if(!allWorkers.isEmpty()) {
            o += showWorkers(allWorkers.getLeftTree());

            o += "Worker: "+allWorkers.getContent().getName()+", Tasks: ";
            if(allWorkers.getContent().getTasks() != null && allWorkers.getContent().getTasks().length>0) {
                o += "{";
                for (int i = 0; i < allWorkers.getContent().getTasks().length; i++) {
                    o += allWorkers.getContent().getTasks()[i].getID() + ";";
                }
                o += "}\n";
            }else{
                o += "No Tasks active.\n";
            }

            o += showWorkers(allWorkers.getRightTree());
        }
        return o;
    }

    public BinarySearchTree getAllWorkers(){
        return allWorker;
    }
}
