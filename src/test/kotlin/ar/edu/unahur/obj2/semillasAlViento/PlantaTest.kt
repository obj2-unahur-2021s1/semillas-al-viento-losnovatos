package ar.edu.unahur.obj2.semillasAlViento

import io.kotest.assertions.throwables.shouldThrowAny
import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.shouldBe

class PlantaTest : DescribeSpec({
    describe("INTA") {
        val menta = Menta(2020, 1f)
        val soja = Soja(2009, 0.6f, false)
        val sojaTransgenica = Soja(2009, 0.9f, true)
        val parcela = Parcela(20, 30, 9)
        val agricultora = Agricultora(mutableListOf<Parcela>())
        agricultora.parcelas.add(parcela)

        describe("Una menta") {
            describe("que tolera 6 horas de sol al día") {
                it("NO es fuerte") {
                    menta.esFuerte().shouldBeFalse()
                }
                it("da nuevas semillas") {
                    menta.daSemillas().shouldBeTrue()
                }
            }
        }

        describe("Una soja") {
            it("tolera 7 horas de sol al día") {
                soja.horasDeSolQueTolera().shouldBe(7)
            }
            it("NO da nuevas semillas") {
                soja.daSemillas().shouldBeFalse()
            }
        }

        describe("Una soja transgenica") {
            it("NO da nuevas semillas") {
                sojaTransgenica.daSemillas().shouldBeFalse()
            }
            it("tolera el doble de horas de sol al día") {
                sojaTransgenica.horasDeSolQueTolera().shouldBe(14)
            }
            it("es fuerte") {
                sojaTransgenica.esFuerte().shouldBeTrue()
            }
        }

        describe("Una parcela de 20m de ancho x 30m de largo") {
            it("tiene 600m de superficie ") {
                parcela.superficie().shouldBe(600)
            }
            it("tolera 230 plantas como maximo") {
                parcela.cantidadMaximaPlantas().shouldBe(230)
            }
            // no se puede realizar el test de tieneComplicaciones de manera correcta,
            // debido a que no está implementado en la clase Parcela dicho método
            // se puede testear de la siguiente manera:
            it("tiene complicaciones") {
                parcela.plantas.add(menta)
                menta.parcelaTieneComplicaciones(parcela).shouldBeTrue()
            }
            // no se puede realizar el test de esSemillera de manera correcta
            // debido a que no está implementado en la clase Parcela,
            // se puede testear de la siguiente manera:
            it("es semillera") {
                agricultora.parcelasSemilleras().shouldContainExactly(parcela)
            }
            // no se puede realizar correctamente el test de plantar debido a que no arroja un error
            // la manera correcta de testearlo es la siguiente:
            /*it("no permite plantar mas plantas si esta llena") {
                repeat(230) {parcela.plantar(menta)}
                shouldThrowAny { parcela.plantar(menta) }
            }*/

            // lo mismo sucede ssi queremos testear el otro error cuando las horas de sol de la Parcela
            // supera por 2 o mas a la cantidad de horas de sol que tolera la planta.
            // la manera correcta de testearlo es la siguiente:
            /*it("no permite plantas si la parcela recibe al menos 2 horas mas de sol que lo que la planta tolera") {
                shouldThrowAny { parcela.plantar(menta) }
            }*/
        }

        describe("Una agricultora") {
            val parcelaMasOcupada = Parcela(1, 2, 5)
            parcelaMasOcupada.plantas.add(menta)
            parcelaMasOcupada.plantas.add(soja)
            it("planta estrategicamente una soja transgenica en parcela mas libre") {
                agricultora.plantarEstrategicamente(sojaTransgenica)
                parcela.plantas.shouldContainExactly(sojaTransgenica)
            }
        }
    }
})
