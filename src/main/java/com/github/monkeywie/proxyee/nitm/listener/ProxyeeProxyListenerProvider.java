package com.github.monkeywie.proxyee.nitm.listener;


public interface ProxyeeProxyListenerProvider {

    ProxyeeProxyListener create();

    Class<? extends ProxyeeProxyListener> listenerClass();


    class Singleton implements ProxyeeProxyListenerProvider {
        private ProxyeeProxyListener listener;

        public Singleton(ProxyeeProxyListener listener) {
            this.listener = listener;
        }

        @Override
        public ProxyeeProxyListener create() {
            return listener;
        }

        @Override
        public Class<? extends ProxyeeProxyListener> listenerClass() {
            return listener.getClass();
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            Singleton singleton = (Singleton) o;

            return listener.equals(singleton.listener);
        }

        @Override
        public int hashCode() {
            return listener.hashCode();
        }

        @Override
        public String toString() {
            return "singleton(" + listener + ")";
        }
    }


}
