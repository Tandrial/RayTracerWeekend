data class Ray(val origin: Vec3, val direction: Vec3) {
  fun pointAt(t: Float) = origin + t * direction
}