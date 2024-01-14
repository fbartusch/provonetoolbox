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

mvn install:install-file \
   -Dfile=/tmp/provtoolbox-build/ProvToolbox/modules-core/prov-jsonld/target/prov-jsonld-2.0.3.jar \
   -DgroupId=org.openprovenance.prov \
   -DartifactId=prov-jsonld \
   -Dversion=2.0.3 \
   -Dpackaging=jar \
   -DgeneratePom=true

mvn install:install-file \
   -Dfile=/tmp/provtoolbox-build/ProvToolbox/modules-core/prov-jsonld-xml/target/prov-jsonld-xml-2.0.3.jar \
   -DgroupId=org.openprovenance.prov \
   -DartifactId=prov-jsonld-jsonld-xml \
   -Dversion=2.0.3 \
   -Dpackaging=jar \
   -DgeneratePom=true

mvn install:install-file \
   -Dfile=/tmp/provtoolbox-build/ProvToolbox/modules-core/prov-n/target/prov-n-2.0.3.jar \
   -DgroupId=org.openprovenance.prov \
   -DartifactId=prov-jsonld-n \
   -Dversion=2.0.3 \
   -Dpackaging=jar \
   -DgeneratePom=true

mvn install:install-file \
   -Dfile=/tmp/provtoolbox-build/ProvToolbox/modules-misc/prov-dot/target/prov-dot-2.0.3.jar \
   -DgroupId=org.openprovenance.prov \
   -DartifactId=prov-dot \
   -Dversion=2.0.3 \
   -Dpackaging=jar \
   -DgeneratePom=true
```