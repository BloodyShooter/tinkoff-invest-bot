package org.gvozdetscky.tinkoffinvestbot.core;

import lombok.Data;

@Data
public class Parameters {

    private final static int ARGUMENTS_NUMBER = 2;
    private String token;
    private boolean sandBoxMode;

    public Parameters(String[] args) {
        if (args.length < ARGUMENTS_NUMBER)
            throw new IllegalArgumentException(
                    String.format("Invalid number of arguments [%d], expected [%d]", args.length, ARGUMENTS_NUMBER)
            );
        setParameters(args[0], Boolean.parseBoolean(args[1]));
    }

    private void setParameters(String token, boolean sandBoxMode) {
        this.token = token;
        this.sandBoxMode = sandBoxMode;
    }

    @Override
    public final String toString() {
        return String.format("core.Parameters: sandBoxMode = %s", sandBoxMode);
    }

}
