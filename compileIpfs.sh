cd model
bash -x compileModel.sh
cd ../

kotlinc IPFS.kt -cp .com/squareup/moshi/moshi-1.4.0.jar:model/MessageWithCode.jar:okhttp-3.11.0.jar 

kotlinc IPFSConnection.kt -cp .com/squareup/moshi/moshi-1.4.0.jar:model/MessageWithCode.jar:okhttp-3.11.0.jar 
