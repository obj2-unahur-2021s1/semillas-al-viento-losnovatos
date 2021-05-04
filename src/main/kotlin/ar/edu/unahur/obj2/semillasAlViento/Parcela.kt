package ar.edu.unahur.obj2.semillasAlViento

class Parcela(val ancho: Int, val largo: Int, val horasSolPorDia: Int) {
  val plantas = mutableListOf<Planta>()
  var cantidadPlantas = 0// un poco bastante redundante.. completamente innecesario, se podria obtener preguntandole el size a la lista de plantas.

  fun superficie() = ancho * largo
  fun cantidadMaximaPlantas() =
    if (ancho > largo) ancho * largo / 5 else ancho * largo / 3 + largo //raro, esta medio feo.. revisar.

  fun plantar(planta: Planta) {
    if (cantidadPlantas == this.cantidadMaximaPlantas()) {
      println("Ya no hay lugar en esta parcela")
    } else if (horasSolPorDia > planta.horasDeSolQueTolera() + 2) {
      println("No se puede plantar esto acá, se va a quemar")
    } else {
      plantas.add(planta)
      cantidadPlantas += 1
    }// FALTA IMPLEMENTACION DE TIENE COMPLICACIONES.
  }
}

class Agricultora(val parcelas: MutableList<Parcela>) {
  var ahorrosEnPesos = 20000// robustez: las tierras no se pueden comprar, ni la agricultora tiene ahorros. genera informacion erratica.

  // Suponemos que una parcela vale 5000 pesos
  fun comprarParcela(parcela: Parcela) {
    if (ahorrosEnPesos >= 5000) {
      parcelas.add(parcela)
      ahorrosEnPesos -= 5000
    }
  }

  fun parcelasSemilleras() =// diuh, asquito.
    parcelas.filter {
      parcela -> parcela.plantas.all {
        planta -> planta.daSemillas()
      }
    }

  fun plantarEstrategicamente(planta: Planta) {
    val laElegida = parcelas.maxBy { it.cantidadMaximaPlantas() - it.cantidadPlantas }!!//falta que la parcela cumpla las condiciones de la planta. SIMPLICIDAD:el nombre laElegida es horrible, no se sabe de que habla.
    laElegida.plantas.add(planta)//esto estaria bueno implementarlo aparte, parcelaEsIdealPara(planta)
  }
}
