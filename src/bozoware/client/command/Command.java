package bozoware.client.command;

public abstract class Command {

    private final String name;
    private final String description;
    private final String[] usages;

    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public String[] getUsages() {
        return usages;
    }

    protected Command() {
        CommandAnnotation annotation = getClass().getAnnotation(CommandAnnotation.class);
        if(annotation == null) {
            throw new IllegalStateException("Instantiating Command "+getClass().getName()+" Without Annotation");
        }

        this.name = annotation.name();
        this.description = annotation.description();
        this.usages = annotation.usages();

    }

    protected abstract void run();

}
