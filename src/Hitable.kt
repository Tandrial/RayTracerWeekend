import kotlin.math.sqrt

interface Hitable {
  fun hit(r: Ray, tMin: Float, tMax: Float): HitRecord
}

data class HitRecord(val t: Float = Float.MAX_VALUE, val p: Vec3 = Vec3(), val normal: Vec3 = Vec3())


data class HitableList(val list: MutableList<Hitable> = mutableListOf()) : Hitable {
  override fun hit(r: Ray, tMin: Float, tMax: Float): HitRecord {
    var rec = HitRecord()
    for (i in 0 until list.count()) {
      val neu = list[i].hit(r, tMin, rec.t)
      if (neu.t != Float.MAX_VALUE) {
        rec = neu
      }
    }
    return rec
  }
}


data class Sphere(val center: Vec3, val radius: Float) : Hitable {
  override fun hit(r: Ray, tMin: Float, tMax: Float): HitRecord {
    val oc = r.origin - center
    val a = Vec3.dot(r.direction, r.direction)
    val b = Vec3.dot(oc, r.direction)
    val c = Vec3.dot(oc, oc) - radius * radius
    val discriminant = b * b - a * c
    if (discriminant > 0f) {
      var tmp = (-b - sqrt(b * b - a * c)) / a
      if (tmp < tMax && tmp > tMin) {
        return HitRecord(tmp, r.pointAt(tmp), (r.pointAt(tmp) - center) / radius)
      }
      tmp = (-b + sqrt(b * b - a * c)) / a
      if (tmp < tMax && tmp > tMin) {
        return HitRecord(tmp, r.pointAt(tmp), (r.pointAt(tmp) - center) / radius)
      }
    }
    return HitRecord()
  }
}