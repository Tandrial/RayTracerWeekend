import java.io.File
import java.lang.StringBuilder

fun color(r: Ray, world: Hitable): Vec3 {
  val rec = world.hit(r, 0f, Float.MAX_VALUE)
  if (rec.t != Float.MAX_VALUE) {
    return 0.5f * Vec3(rec.normal.x + 1, rec.normal.y + 1, rec.normal.z + 1)
  } else {
    val unitDirection = Vec3.unitVec(r.direction)
    val t = (0.5 * (unitDirection.y + 1)).toFloat()
    return (1.0 - t).toFloat() * Vec3(1f, 1f, 1f) + t * (Vec3(0.5f, 0.7f, 1f))
  }
}

fun main() {
  val ee = Vec3(3f, 6f, 9f)
  val e2 = Vec3(9f, 12f, 18f)
  println(ee / 3f)
  println(e2 / ee)

  val nx = 200
  val ny = 100
  val output = StringBuilder()
  output.append("P3\n$nx $ny\n255\n")
  val lowerLeft = Vec3(-2f, -1f, -1f)
  val horizontal = Vec3(x = 4f)
  val vertical = Vec3(y = 2f)
  val origin = Vec3()
  val world = HitableList(
    mutableListOf(
      Sphere(Vec3(z = -1f), 0.5f),
      Sphere(Vec3(y = -100.5f, z = -2f), 100f)
    )
  )
  for (j in ny - 1 downTo 0) {
    for (i in 0 until nx) {
      val u = i.toFloat() / nx
      val v = j.toFloat() / ny
      val r = Ray(origin, lowerLeft + u * horizontal + v * vertical)
      val p = r.pointAt(2f)
      val col = color(r, world)
      val ir = (255.99f * col[0]).toInt()
      val ig = (255.99f * col[1]).toInt()
      val ib = (255.99f * col[2]).toInt()

      output.append("$ir $ig $ib\n")
    }
  }
  File("C:\\Users\\tan\\Desktop\\test.ppm").writeText(output.toString())
  File("test.ppm").writeText(output.toString())
}