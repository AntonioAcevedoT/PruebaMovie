
    enum class Status {
        RUNNING,
        SUCCESS,
        FAILED
    }

    class NetworkState(val status: Status, val msg: String) {

        companion object {

            val LOADED: NetworkState
            val LOADING: NetworkState

            init {
                LOADED =
                    NetworkState(
                        Status.SUCCESS,
                        "Exitoso"
                    )
                LOADING =
                    NetworkState(
                        Status.RUNNING,
                        "Cargando"
                    )

            }
        }

    }
