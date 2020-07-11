package io.github.oguzhancevik.obj2gltf;

import io.github.oguzhancevik.obj2gltf.obj.BufferStrategy;
import io.github.oguzhancevik.obj2gltf.obj.GltfWriteType;
import io.github.oguzhancevik.obj2gltf.obj.IndicesComponentType;
import org.junit.Test;

public class ConvertObjToGltfTest {

    @Test
    public void testConvertObjToGltf() {
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
    }

    @Test
    public void testConvertObjToGltfWithEmbedded() {
        String inputObjFilePath = "src/main/resources/model/model.obj";
        String inputMtlFileName = "model";
        String outputFilePath = "src/main/resources/model/output_embedded/";
        String outputFileName = "model";
        BufferStrategy bufferStrategy = BufferStrategy.BUFFER_PER_FILE;
        IndicesComponentType indicesComponentType = IndicesComponentType.GL_UNSIGNED_SHORT;
        GltfWriteType gltfWriteType = GltfWriteType.EMBEDDED;

        ConvertObjToGltf convertObjToGltf = new ConvertObjToGltf.Builder().inputObjFilePath(inputObjFilePath)
                .inputMtlFileName(inputMtlFileName).outputFilePath(outputFilePath).outputFileName(outputFileName)
                .bufferStrategy(bufferStrategy).indicesComponentType(indicesComponentType).gltfWriteType(gltfWriteType).build();

        convertObjToGltf.convert();
    }
}
