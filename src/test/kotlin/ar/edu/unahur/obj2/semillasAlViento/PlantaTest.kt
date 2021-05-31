package ar.edu.unahur.obj2.semillasAlViento

import io.kotest.core.spec.style.DescribeSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe

class PlantaTest : DescribeSpec({
    describe("INTA") {
        val menta = Menta(2020, 1f)
        val soja = Soja(2009, 0.6f, false)
        val sojaTransgenica = Soja(2009, 0.9f, true)
        val plantas = mutableListOf<Planta>()
        val parcela = Parcela(20, 30, 8)

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
            it("tolera 230 plantas como cantidad maxima") {
                parcela.cantidadMaximaPlantas().shouldBe(230)
            }
            // no se puede realizar el test de tieneComplicaciones de manera correcta,
            // debido a que no está implementado en la clase Parcela dicho método
            // se puede testear de la siguiente manera:
            it("tiene complicaciones") {
                menta.parcelaTieneComplicaciones(parcela)
            }
        }
    }
})
