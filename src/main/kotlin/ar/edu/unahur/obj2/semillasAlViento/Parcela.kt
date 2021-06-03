package ar.edu.unahur.obj2.semillasAlViento

class Parcela(val ancho: Int, val largo: Int, val horasSolPorDia: Int) {
  val plantas = mutableListOf<Planta>()
  var cantidadPlantas =plantas.size
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
  fun tieneComplicaciones() =
    plantas.any { it.horasDeSolQueTolera() < horasSolPorDia }
}

class Agricultora(val parcelas: MutableList<Parcela> /*debería ser inmutable la lista, ya que no se pueden comprar ni vender*/) {
  var ahorrosEnPesos = 20000// robustez: las tierras no se pueden comprar, ni la agricultora tiene ahorros. genera informacion erratica.

  // Suponemos que una parcela vale 5000 pesos
  fun comprarParcela(parcela: Parcela) {
    if (ahorrosEnPesos >= 5000) {
      parcelas.add(parcela)
      ahorrosEnPesos -= 5000
    }
  } //Simplicidad. No se requiere en ningún momento esta función y se aclara que para este ejercicio no se venden ni se compran parcelas.

  fun parcelasSemilleras() =// diuh, asquito. (Des)Acoplamiento. El calculo esSemillera() debería estar en la Parcela.
    parcelas.filter {
      parcela -> parcela.plantas.all {
        planta -> planta.daSemillas()
      }
    }

  fun plantarEstrategicamente(planta: Planta) {
    val laElegida = parcelas.maxBy { it.cantidadMaximaPlantas() - it.cantidadPlantas }!!//falta que la parcela cumpla las condiciones de la planta. Cohesión: ésto de buscar la parcela debería estar en otro método.
    laElegida.plantas.add(planta)//acá no está utilizando el método plantar(planta) que hace lo mismo, pero con las restricciones de la parcela. Acomplamiento.
  }
}
