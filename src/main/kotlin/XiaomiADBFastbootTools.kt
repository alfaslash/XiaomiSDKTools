import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.scene.image.Image
import javafx.stage.Stage
import kotlinx.coroutines.runBlocking
import java.io.File


class XiaomiSDKTools : Application() {

    companion object {
        const val version = "1.0.0"
        val dir = File(System.getProperty("user.home"), "XiaomiSDKTools")
        val win = "win" in System.getProperty("os.name").toLowerCase()
        val linux = "linux" in System.getProperty("os.name").toLowerCase()

        @JvmStatic
        fun main(args: Array<String>) {
            launch(XiaomiSDKTools::class.java)
        }
    }

    init {
        dir.mkdir()
    }

    @Throws(Exception::class)
    override fun start(stage: Stage) {
        stage.scene = Scene(FXMLLoader.load(javaClass.classLoader.getResource("Main.fxml")))
        stage.title = "Xiaomi SDK Tools"
        stage.icons.add(Image("icon.png"))
        stage.show()
    }

    override fun stop() {
        runBlocking {
            try {
                Command.exec(mutableListOf("adb", "kill-server"))
            } catch (e: Exception) {
                // OK
            }
        }
    }

}
