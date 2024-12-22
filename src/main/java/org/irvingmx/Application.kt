package org.irvingmx

import io.ktor.server.application.*
import io.ktor.server.plugins.cors.CORS
import io.ktor.http.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.engine.*
import io.ktor.server.http.content.*
import io.ktor.server.netty.*

fun main() {
    embeddedServer(Netty, port = 8080) {
        module()
    }.start(wait = true)
}

fun Application.module() {


    install(CORS) {
        anyHost() // Allows requests from any host. You can restrict this to specific hosts if needed.
        allowHeader(HttpHeaders.ContentType)
    }
    val helloService = HelloService()

    routing {

        get("/") {
            val name = call.request.queryParameters["name"]
            if (name != null) {
                val response = helloService.getHello(name)
                call.respondText(response, ContentType.Text.Plain)
            } else {
                call.respondText("Hello, World!", ContentType.Text.Plain)
            }
        }

        get("/irving") {
            call.respondText("Hello, Irving!", ContentType.Text.Plain)
        }

        static("/") {
            resources("static")
        }
    }


}

