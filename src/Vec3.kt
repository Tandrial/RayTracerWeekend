import kotlin.math.sqrt

operator fun Float.times(other: Vec3) = Vec3(this * other.x, this * other.y, this * other.z)

data class Vec3(var x: Float = 0f, var y: Float = 0f, var z: Float = 0f) {

  companion object {
    fun unitVec(a: Vec3) = a / a.length()
    fun dot(a: Vec3, b: Vec3)  = a.x * b.x + b.y * b.y + a.z * b.z
    fun cross(a: Vec3, b: Vec3) = Vec3(a.y * b.z - a.z * b.z, -(a.x * b.z - a.z * b.x), a.x * b.y - a.y * b.x)
  }

  operator fun unaryPlus() = this
  operator fun unaryMinus() = Vec3(-x, -y, -z)

  operator fun plus(other: Vec3) = Vec3(x + other.x, y + other.y, z + other.z)
  operator fun minus(other: Vec3) = Vec3(x - other.x, y - other.y, z - other.z)

  operator fun times(other: Vec3) = Vec3(x * other.x, y * other.y, z * other.z)
  operator fun times(factor: Float) = Vec3(factor * x, factor * y, factor * z)

  operator fun div(other: Vec3) = Vec3(x / other.x, y / other.y, z / other.z)
  operator fun div(factor: Float) = Vec3(x / factor, y / factor, z / factor)

  operator fun plusAssign(other: Vec3) {
    x += other.x
    y += other.y
    z += other.z
    this
  }

  operator fun minusAssign(other: Vec3) {
    x -= other.x
    y -= other.y
    z -= other.z
    this
  }

  operator fun timesAssign(other: Vec3) {
    x *= other.x
    y *= other.y
    z *= other.z
    this
  }

  operator fun timesAssign(scale: Float) {
    x *= scale
    y *= scale
    z *= scale
    this
  }

  operator fun divAssign(other: Vec3) {
    x /= other.x
    y /= other.y
    z /= other.z
    this
  }

  operator fun divAssign(scale: Float) {
    x /= scale
    y /= scale
    z /= scale
    this
  }

  operator fun get(idx: Int): Float {
    return when (idx) {
      0 -> x
      1 -> y
      2 -> z
      else -> throw ArrayIndexOutOfBoundsException("idx needs to be 0, 1 or 2 but was $idx!")
    }
  }

  fun length(): Float = sqrt(x * x + y * y + z * z)
  fun squaredLength() = x * x + y * y + z * z

  override fun toString(): String = "<$x,$y,$z>"
}
