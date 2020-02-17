# Minichain : blockchain in kotlin using fork from (https://github.com/ligi/ipfs-api-kotlin) 

# sdkman
## installation sdkman
 https://sdkman.io/install

 $ curl -s "https://get.sdkman.io" | bash

## usage sdkman
  sdk list <package>
  sdk uninstall <package> x.y.z
  sdk install <package>
  sdk use gradle <package> x.y.z
  
## cleanup cache archives
  sdk flush archives

# gradle
## installation gradle

  sdk install gradle

# java 
## installation java
  sdk use java 19.3.0.2.r8-grl  (to be coherent with jacoco)

# kotlin
## installation kotlin

  sdk install kotlin
  
# ipfs-api-kotlin
 (https://github.com/ligi/ipfs-api-kotlin) [ ligi / ipfs-api-kotlin ]
  git clone https://github.com/ligi/ipfs-api-kotlin.git
  
# kotlin-minichain

  git clone https://github.com/GustaveNimant/kotlin-minichain.git
  
## Installation kotlin-minichain

# Compiling
  cd ~/sources/kotlin-minichain
  gradlew build 

# Running tests
  cd ~/sources/kotlin-minichain
  gradlew test 

# License 

MIT
