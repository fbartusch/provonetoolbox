# ProvOneToolbox

## Installation

### Install provtoolbox jars

I built the needed provtoolbox `jar` files and added it to the local Maven repository:

```
mvn install:install-file \
   -Dfile=/tmp/provtoolbox-build/ProvToolbox/modules-core/prov-model/target/prov-model-2.0.3.jar \
   -DgroupId=org.openprovenance.prov \
   -DartifactId=prov-model \
   -Dversion=2.0.3 \
   -Dpackaging=jar \
   -DgeneratePom=true

mvn install:install-file \
   -Dfile=/tmp/provtoolbox-build/ProvToolbox/modules-executable/prov-interop/target/prov-interop-2.0.3.jar \
   -DgroupId=org.openprovenance.prov \
   -DartifactId=prov-interop \
   -Dversion=2.0.3 \
   -Dpackaging=jar \
   -DgeneratePom=true
```