package ar.edu.unahur.obj2.semillasAlViento

class Parcela(val ancho: Int, val largo: Int, val horasSolPorDia: Int) {
  val plantas = mutableListOf<Planta>()
  var cantidadPlantas =plantas.size // no hace falta utilizar una variable para esto.
  fun superficie() = ancho * largo
  fun cantidadMaximaPlantas() =
    if (ancho > largo) superficie() / 5 else superficie() / 3 + largo

  fun plantar(planta: Planta) {
    if (cantidadPlantas == this.cantidadMaximaPlantas()) {
      error("Ya no hay lugar en esta parcela")
    } else if (horasSolPorDia > planta.horasDeSolQueTolera() + 2) {
      error("No se puede plantar esto acá, se va a quemar")
    } else {
      plantas.add(planta)
    }
  }
  fun tieneComplicaciones() = plantas.any { it.horasDeSolQueTolera() < horasSolPorDia }
  fun esSemillera() = plantas.all { planta -> planta.daSemillas() }
}

class Agricultora(val parcelas: List<Parcela>) {

  fun parcelasSemilleras() = parcelas.filter { parcela -> parcela.esSemillera() }

  fun plantarEstrategicamente(planta: Planta) {
    this.parcelaMasLibre().plantar(planta)
  }
  fun parcelaMasLibre() = parcelas.maxBy { it.cantidadMaximaPlantas() - it.cantidadPlantas }!!
}
