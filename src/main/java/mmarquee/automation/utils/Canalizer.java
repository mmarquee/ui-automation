/*
 * Copyright 2017 inpwtepydjuf@gmail.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package mmarquee.automation.utils;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Wraps an interface instance in a way that all calls to interface methods originate from the same thread.
 *
 * @author Pascal Bihler
 *
 */
public class Canalizer {
    /** The thread where all calls originate from. */
    static final ExecutorService executor = Executors.newFixedThreadPool(1, r -> {
        Thread t = Executors.defaultThreadFactory().newThread(r);
        t.setDaemon(true);
        return t;
    });

    /**
     * Shutdown the ExecutorService nicely.
     */
    public static void shutdown() {
        executor.shutdown();
    }

    /**
     * Wraps an interface instance in a way that all calls to interface methods originate from the same thread.
     *
     * @param <T> The type of the Object to canalize
     * @param plainInstance The instance to canalize
     * @return the canalized instance
     */
    @SuppressWarnings("unchecked")
    public static <T extends Object> T canalize(final T plainInstance) {
        final CanalizerInvocationHandler invocationHandler = new CanalizerInvocationHandler(executor,plainInstance);
        return (T) java.lang.reflect.Proxy
                .newProxyInstance(plainInstance.getClass().getClassLoader(),
                        getInterfaces(plainInstance),
                        invocationHandler);
    }

    private static Class<?>[] getInterfaces(final Object target) {
        Class<?> base = target.getClass();
        final Set<Class<?>> interfaces = new HashSet<>();
        if (base.isInterface()) {
            interfaces.add(base);
        }
        while (base != null && !Object.class.equals(base)) {
            interfaces.addAll(Arrays.asList(base.getInterfaces()));
            base = base.getSuperclass();
        }
        return interfaces.toArray(new Class[0]);
    }

    static class CanalizerInvocationHandler implements InvocationHandler {
        private ExecutorService executor;
        private Object underlying;

        public CanalizerInvocationHandler(final ExecutorService executor, final Object underlying) {
            this.executor = executor;
            this.underlying = underlying;
        }

        @Override
        public Object invoke(final Object proxy,
                             final Method method,
                             final Object[] args)
                throws Throwable {
            final Callable<Object> task = () -> method.invoke(underlying, args);

            final Future<Object> result = executor.submit(task);

            try {
                return result.get();
            } catch (final ExecutionException ex) {
                throw ex.getCause();
            }
        }
    }
}
