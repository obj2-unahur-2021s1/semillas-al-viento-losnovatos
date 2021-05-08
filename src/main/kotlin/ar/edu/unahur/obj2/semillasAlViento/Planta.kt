package ar.edu.unahur.obj2.semillasAlViento

abstract class Planta(val anioObtencionSemilla: Int, var altura: Float) {// mutacion controlada:la altura no deberia cambiar nunca,tendria que ser val.
  fun esFuerte() = this.horasDeSolQueTolera() > 10

  fun parcelaTieneComplicaciones(parcela: Parcela) =
    parcela.plantas.any { it.horasDeSolQueTolera() < parcela.horasSolPorDia }
  // este metodo debería estar en la clase de Parcela, no es responsabilidad de la Planta. Cohesión.

  abstract fun horasDeSolQueTolera(): Int
  abstract fun daSemillas(): Boolean//el es fuerte es condicion para todas, por lo tanto se podria poner aca y usar un super en la sub con la otra condición. -> Redundancia Mínima
}

class Menta(anioObtencionSemilla: Int, altura: Float) : Planta(anioObtencionSemilla, altura) {
  override fun horasDeSolQueTolera() = 6
  override fun daSemillas() = this.esFuerte() || altura > 0.4
}

class Soja(anioObtencionSemilla: Int, altura: Float, val esTransgenica: Boolean /* la SojaTransgenica debería ser otra clase que sea del tipo Soja.*/ ) : Planta(anioObtencionSemilla, altura) {
  override fun horasDeSolQueTolera(): Int  {
    // ¡Magia de Kotlin! El `when` es como un `if` pero más poderoso:
    // evalúa cada línea en orden y devuelve lo que está después de la flecha.
    val horasBase = when {
      altura < 0.5  -> 6
      altura < 1    -> 7
      else          -> 9
    }

    return if (esTransgenica) horasBase * 2 else horasBase
  }


  override fun daSemillas(): Boolean  {
    if (this.esTransgenica) {
      return false
    }

    return this.esFuerte() || (this.anioObtencionSemilla > 2007 && this.altura > 1)
  }
}
