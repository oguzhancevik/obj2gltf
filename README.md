### OBJ to glTF converter for Java :arrows_counterclockwise:

This is a library for converting OBJ files into glTF assets. You can convert OBJ file embedded or binary.

Usage examples :heavy_check_mark:

* Convert OBJ file binary 

 `

         String inputObjFilePath = "src/main/resources/model/model.obj";
         String inputMtlFileName = "model";
         String outputFilePath = "src/main/resources/model/output_binary/";
         String outputFileName = "model";
         BufferStrategy bufferStrategy = BufferStrategy.BUFFER_PER_FILE;
         IndicesComponentType indicesComponentType = IndicesComponentType.GL_UNSIGNED_SHORT;
 
         ConvertObjToGltf convertObjToGltf = new ConvertObjToGltf.Builder().inputObjFilePath(inputObjFilePath)
                 .inputMtlFileName(inputMtlFileName).outputFilePath(outputFilePath).outputFileName(outputFileName)
                 .bufferStrategy(bufferStrategy).indicesComponentType(indicesComponentType).build();
         
         convertObjToGltf.convert();
 `

* Convert OBJ file embedded 

 `

         String inputObjFilePath = "src/main/resources/model/model.obj";
         String inputMtlFileName = "model";
         String outputFilePath = "src/main/resources/model/output_binary/";
         String outputFileName = "model";
         BufferStrategy bufferStrategy = BufferStrategy.BUFFER_PER_FILE;
         IndicesComponentType indicesComponentType = IndicesComponentType.GL_UNSIGNED_SHORT;
         GltfWriteType gltfWriteType = GltfWriteType.EMBEDDED;
 
         ConvertObjToGltf convertObjToGltf = new ConvertObjToGltf.Builder().inputObjFilePath(inputObjFilePath)
                 .inputMtlFileName(inputMtlFileName).outputFilePath(outputFilePath).outputFileName(outputFileName)
                 .bufferStrategy(bufferStrategy).indicesComponentType(indicesComponentType).gltfWriteType(gltfWriteType).build();
         
         convertObjToGltf.convert();
 `
 
You can see all of usage on ConvertObjToGltfTest.java 

You can see available input OBJ files and output files on src/main/resources/model/

Thanks https://github.com/javagl/JglTF/tree/master/jgltf-obj 