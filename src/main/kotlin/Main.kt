import com.machinezoo.sourceafis.FingerprintMatcher
import com.machinezoo.sourceafis.FingerprintTemplate
import java.nio.file.Files
import java.nio.file.Paths


class Main {
    companion object {
        @JvmStatic fun main(args: Array<String>) {
            val imgPath = "D:\\Project\\testKotlinFingerprint\\img"
            val imgCount = 128
            val f = Array(imgCount, {_ -> FingerprintTemplate()})

            for (i in 0 until imgCount) {
                val img = Files.readAllBytes(Paths.get("$imgPath\\img${i+1}.jpg"))
                f[i] = FingerprintTemplate().dpi(500.0).create(img)
            }

            for (i in 0 until imgCount - 1) {
                print("fingerprint ${i+1} matched with : ")
                for (j in i + 1 until imgCount) {
                    val score = FingerprintMatcher().index(f[i]).match(f[j])

                    //if score > 40, it is matched with confidence 99.99%
                    if (score > 40)
                        print("${j+1}")

                    print(' ')
                }

                println()
            }
        }
    }
}