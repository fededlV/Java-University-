### Enunciado de Herecia para Seguir Practicando ###

Un **estudio jurídico** desea construir un sistema, con la tecnología de objetos, que permita administrar las
causas que lleva adelante y liquidar los haberes a los abogados que trabajan en estas causas, en función de
los fallos que obtengan en las mismas. Las causas se abren por acción del demandante que se presenta en el estudio y
decide, con el asesoramiento de los abogados, iniciar una demanda en uno de los fueros procesales: Civil o Penal.

Esta **causa** tendrá: un número, el nombre del demandante, estado (1: Abierta - 2: Cerrada), sentencia (1:
favorable - 2: no favorable), fuero (1: Civil - 2: Penal) y monto.

Cuando se abre una causa, el estudio se la asigna a un abogado según su especialidad, que es el
fuero en que desarrolla sus actividades. Teniendo en cuenta esto, el estudio le asigna la causa al
abogado que menor cantidad de causas abiertas tenga al momento. En el caso en no exista un
abogado disponible para el fuero de la causa, se desestima la apertura de la misma.

Con respecto a los **abogados** el estudio registra los siguientes datos: número de documento, apellido,
nombre, dirección de correo y causas que le fueron asignadas. Los abogados **Penalistas** cobran un
monto fijo por cada causa favorable que tuvieron a su cargo, mientras que los abogados **Civiles** cobran
un porcentaje sobre el monto total obtenido por causas con sentencia favorables que tuvieron a cargo. Tanto
los montos fijos como los porcentajes son diferentes para cada abogado.
Las causas siguen su curso y, cuando terminan, se registra su cierre con los datos de la sentencia
obtenida (favorable o no favorable) y el monto que se obtuvo en la misma. Periódicamente, el estudio realiza la 
liquidación de los haberes de los abogados, sobre la base de las causas con sentencia favorable que obtuvieron a 
su cargo.

El estudio desea automatizar estas funciones, por lo que solicita que se construya una aplicación que
permita:
 - Abrir una causa. Este método debe seleccionar el abogado que tiene que llevar la causa, según el fuero que 
corresponde y que tenga mayor disponibilidad (es decir, con menor cantidad de causas abiertas), incorporando la 
causa a la lista de causas del abogado. Si hubiese más de un abogado con el menor número de causas abiertas, 
se le asignará a cualquiera de ellos.
 - Cerrar una causa a partir de un número.
 - Liquidar haberes de un abogado identificado por su número de documento.