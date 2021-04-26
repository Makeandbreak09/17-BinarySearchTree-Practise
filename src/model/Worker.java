package model;

//TODO 01: Setze das Interface um. Finde heraus, was der Suchschlüssel eines jeden Worker-Objekts ist.
public class Worker implements ComparableContent<Worker>{
    private String name;
    private Queue<Task> allTasks;

    public Worker(String name){
        this.name = name;
        allTasks = new Queue<>();
    }

    public String getName(){
        return name;
    }

    public void addTask(int id){
        allTasks.enqueue(new Task(id));
    }

    public Task completeTask(){
        Task t = allTasks.front();
        allTasks.dequeue();
        return t;
    }

    public void completeAllTasks(){
        while (!allTasks.isEmpty()){
            completeTask();
        }
    }

    public Task[] getTasks(){
        int a = 0;
        Queue<Task> help = new Queue<>();
        while (!allTasks.isEmpty()){
            a++;
            help.enqueue(allTasks.front());
            allTasks.dequeue();
        }
        Task[] o = null;
        if(a>0) {
            o = new Task[a];
            while (!help.isEmpty()) {
                o[o.length - a] = help.front();
                allTasks.enqueue(help.front());
                help.dequeue();
                a--;
            }
        }
        return o;
    }

    @Override
    public boolean isGreater(Worker pContent) {
        if(name.compareTo(pContent.getName())>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean isEqual(Worker pContent) {
        if(name.compareTo(pContent.getName())==0){
            return true;
        }
        return false;
    }

    @Override
    public boolean isLess(Worker pContent) {
        if(name.compareTo(pContent.getName())<0){
            return true;
        }
        return false;
    }
}
