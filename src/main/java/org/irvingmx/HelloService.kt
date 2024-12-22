package org.irvingmx

class HelloService {

    fun getHello(name: String): String {
        return """{"message": "Hola $name"}"""
    }
}