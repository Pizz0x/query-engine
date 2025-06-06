package Models

import scala.compiletime.ops.double
import scala.collection.BuildFrom.buildFromString


case class Homomorphism(val source: Set[Term], val destination: Set[Term], val map: Map[Term, Term]):
    override def toString(): String =
        map.map { case (key, value) =>
            s"${key.toString} to ${value.toString}"
        }.mkString("\n")



    def isMapValidForSource(): Boolean = 
        map.keys.forall(source.contains)

    def isMapValidForDestination(): Boolean = 
        map.values.forall(destination.contains)
    
    def isActiveDomainVariablesMapped(): Boolean = 
        source.filter(u => u.termType == TermType.Var).forall(map.contains)

    def isActiveDomainConstantMapped(): Boolean = 
    source
      .filter(_.termType == TermType.Cons)
      .forall(item => map.get(item) == Some(item))

    def isValid(): Boolean = 
        isMapValidForSource()
        && isMapValidForDestination()
        && isActiveDomainVariablesMapped()
        && isActiveDomainConstantMapped()