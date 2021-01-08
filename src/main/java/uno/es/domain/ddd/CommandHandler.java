package uno.es.domain.ddd;

public interface CommandHandler<R extends CommandResponse, C extends Command> {

    R handle(C command);

    Class listenTo();
}
