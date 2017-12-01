package com.yadro.server;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * Date: 01.12.17
 *
 * @author olerom
 */
public class InterfaceNamesWrapper {
    @NotNull
    final private List<String> interfaceNames;

    public InterfaceNamesWrapper(@NotNull List<String> interfaceNames) {
        this.interfaceNames = interfaceNames;
    }

    public List<String> getInterfaceNames() {
        return interfaceNames;
    }
}
