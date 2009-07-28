/*
 * Copyright (c) 2008 Haulmont Technology Ltd. All Rights Reserved.
 * Haulmont Technology proprietary and confidential.
 * Use is subject to license terms.

 * Author: Konstantin Krivopustov
 * Created: 28.07.2009 10:09:57
 *
 * $Id$
 */
package com.haulmont.cuba.web.exception;

import com.itmill.toolkit.terminal.Terminal;
import com.haulmont.cuba.web.App;

public abstract class AbstractExceptionHandler implements ExceptionHandler {

    private final Class<? extends Throwable> tClass;

    public AbstractExceptionHandler(Class<? extends Throwable> tClass) {
        this.tClass = tClass;
    }

    public boolean handle(Terminal.ErrorEvent event, App app) {
        Throwable t = event.getThrowable();
        while (t != null) {
            if (tClass.isAssignableFrom(t.getClass())) {
                doHandle(app);
                return true;
            }
            t = t.getCause();
        }
        return false;
    }

    protected abstract void doHandle(App app);
}
