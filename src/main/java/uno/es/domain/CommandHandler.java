package uno.es.domain;

public interface CommandHandler<R extends CommandResponse, C extends Command> {

    R handle(C command);

    Class listenTo();
}
