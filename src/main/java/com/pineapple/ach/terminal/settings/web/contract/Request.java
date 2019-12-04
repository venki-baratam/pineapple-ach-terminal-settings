
package com.pineapple.ach.terminal.settings.web.contract;

import javax.validation.constraints.NotNull;

public class Request {

    @NotNull
    protected Integer terminalId;

    public Integer getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(Integer terminalId) {
        this.terminalId = terminalId;
    }

}
