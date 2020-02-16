## Merging 2 blockchains

# $Source: /public/docs/mychelium/merging-blocks.md$

# definition ...

  resolve converts a mutable to an immutable
  
1. ipns'mutable :
   mutable = variable content which has a signed reference to a immutable,
             and is placed at the address of the hash of the signer's public key.
             (mutables need to be published via gossip protocol)

   mutable = /ipns/Qm....Rve hash of signer's public key
   ipns'mutable : dweb:/ipns/QmcfHufAK9ErQ9ZKJF7YX68KntYYBJngkGDoVKcZEJyRve
   
2. mfs mutable (source) : michel@mfs:/public/myfile.txt
   /public ipfs adress of michel ipfs name space (virtual)
   mutable == variable content which place at a fixed path on mfs
   (mutables need to be published via owner's peerid)

3. ipms mutable (ipfs) :
   ipms'mutable: /ipfs/z6D1ERe2CSjzD4PGUVxgezuVs1cXJreBh8nNApCAEwfr
   mutable == variable content which has a signed reference to a immutable, and is placed
              at the address "mutable-hash" on IPFS network i.e. fixed address

   mutuable-hash(content1) == mutable-hash(content2) 

4. local file system mutable
   local-fs'mutable: file://home/michelc/testing/minichain/README.md



 history = fichier mutable
 
 addr = hash(data)

 node   = hash(block) (\/ node ] block = ipfs file)
 vertex = hash(payload)

 container 
   block = [payload,meta] any kind of document 
   meta = [\previous,...]
 
   payload = block w/o immutable metadata (previous, ...)
            (block may contains mutables)
	     keywords may be anywhere
             keywords are emptied 
	     
 parents = "node" of vertices payload inheritance
         = payload of previous if no merge
         = parents[0] = previous

  
# Blockchain : capitalized word are modified, lowercase are unchanged

# Keyword  substituted according to case

    reserved 
      $mutable : ...  $
      $tic : ...  $
      $qm  : ...  $
      $parents

    physical
      $ slip
      $ snip
      $ spot
      
    immutable 
      $Previous : ... $
      $Date     : ... $  (user)
            
    mutable
      $Source : ...  $  MFS
      $Name   : ...  $  BlockChain Name
      $Next
      $Author : ...  $
    user_defined
      $ ... $

# setup:

1.  blockchain A, owner: michel;
 
 A: genesis -> doc_v0 -> doc_v1 <= HEAD-A

2.  emile forks the blockchain A into blockchain B and create doc_v2b

 B: genesis -> doc_v0 -> doc_v1 -> doc_v2b <= HEAD-B


3.  michel continues to update blockchain A (merging doc_v2b & doc_v2a -> doc_v3_0a):

 A: genesis -> doc_v0 -> doc_v1 -> doc_v2a -> doc_v3_0a -> doc_v3_1a <= HEAD-A

4.  emile adds doc_v3b to blockchain B :

 B: genesis -> doc_v0 -> doc_v1 -> doc_v2b -> doc_v3b  <= HEAD-B

5.  emile wants to merge doc_v3_1a adding doc_v4b to blockchain B :

    a. find common ancestors on vertices graph between doc_v3b and doc_v3_1a = doc_v2b

    b. 


 


 



