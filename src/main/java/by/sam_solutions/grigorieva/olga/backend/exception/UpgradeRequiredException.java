package by.sam_solutions.grigorieva.olga.backend.exception;

public class UpgradeRequiredException extends BusinessException {

    public UpgradeRequiredException() {
        super("upgrade.required");
    }
}
