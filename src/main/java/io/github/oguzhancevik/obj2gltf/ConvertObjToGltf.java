package io.github.oguzhancevik.obj2gltf;

import de.javagl.jgltf.model.GltfConstants;
import de.javagl.jgltf.model.GltfModel;
import de.javagl.jgltf.model.GltfModels;
import de.javagl.jgltf.model.io.GltfAsset;
import de.javagl.jgltf.model.io.GltfModelWriter;
import io.github.oguzhancevik.obj2gltf.creator.ObjGltfAssetCreatorV2;
import io.github.oguzhancevik.obj2gltf.obj.BufferStrategy;
import io.github.oguzhancevik.obj2gltf.obj.GltfWriteType;
import io.github.oguzhancevik.obj2gltf.obj.IndicesComponentType;

import java.io.File;
import java.net.URI;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

public class ConvertObjToGltf {

    private final String inputObjFilePath, inputMtlFileName, outputFilePath, outputFileName;
    private final BufferStrategy bufferStrategy;
    private final IndicesComponentType indicesComponentType;
    private final GltfWriteType gltfWriteType;


    public ConvertObjToGltf(Builder builder) {
        this.inputObjFilePath = builder.inputObjFilePath;
        this.inputMtlFileName = builder.inputMtlFileName;
        this.outputFilePath = builder.outputFilePath;
        this.outputFileName = builder.outputFileName;
        this.bufferStrategy = builder.bufferStrategy;
        this.indicesComponentType = builder.indicesComponentType;
        this.gltfWriteType = builder.gltfWriteType;
    }

    public static class Builder {
        private String inputObjFilePath, inputMtlFileName, outputFilePath, outputFileName;
        private BufferStrategy bufferStrategy;
        private IndicesComponentType indicesComponentType;
        private GltfWriteType gltfWriteType;

        public Builder inputObjFilePath(String inputObjFilePath) {
            this.inputObjFilePath = inputObjFilePath;
            return this;
        }

        public Builder inputMtlFileName(String inputMtlFileName) {
            this.inputMtlFileName = inputMtlFileName;
            return this;
        }

        public Builder outputFilePath(String outputFilePath) {
            this.outputFilePath = outputFilePath;
            return this;
        }

        public Builder outputFileName(String outputFileName) {
            this.outputFileName = outputFileName;
            return this;
        }

        public Builder bufferStrategy(BufferStrategy bufferStrategy) {
            this.bufferStrategy = bufferStrategy;
            return this;
        }

        public Builder indicesComponentType(IndicesComponentType indicesComponentType) {
            this.indicesComponentType = indicesComponentType;
            return this;
        }

        public Builder gltfWriteType(GltfWriteType gltfWriteType) {
            this.gltfWriteType = gltfWriteType;
            return this;
        }

        public ConvertObjToGltf build() {
            return new ConvertObjToGltf(this);
        }

    }

    public void convert() {
        try {

            long startTime = System.nanoTime();

            if (inputObjFilePath == null || inputObjFilePath.isEmpty())
                throw new NullPointerException("inputObjFilePath cannot be empty!");
            URI objUri = Paths.get(inputObjFilePath).toUri();

            if (bufferStrategy == null) throw new NullPointerException("bufferStrategy cannot be empty!");
            ObjGltfAssetCreatorV2 gltfAssetCreator = new ObjGltfAssetCreatorV2(bufferStrategy);

            if (indicesComponentType == null) throw new NullPointerException("indicesComponentType cannot be empty!");
            else if (indicesComponentType == IndicesComponentType.GL_UNSIGNED_BYTE)
                gltfAssetCreator.setIndicesComponentType(GltfConstants.GL_UNSIGNED_BYTE);
            else if (indicesComponentType == IndicesComponentType.GL_UNSIGNED_SHORT)
                gltfAssetCreator.setIndicesComponentType(GltfConstants.GL_UNSIGNED_SHORT);
            else if (indicesComponentType == IndicesComponentType.GL_UNSIGNED_INT)
                gltfAssetCreator.setIndicesComponentType(GltfConstants.GL_UNSIGNED_INT);

            GltfAsset gltfAsset = gltfAssetCreator.create(objUri, inputMtlFileName);

            GltfModel gltfModel = GltfModels.create(gltfAsset);

            GltfModelWriter gltfModelWriter = new GltfModelWriter();

            if (outputFilePath == null || outputFilePath.isEmpty())
                throw new NullPointerException("outputFilePath cannot be empty!");
            if (outputFileName == null || outputFileName.isEmpty())
                throw new NullPointerException("outputFileName cannot be empty!");
            File outputFile = new File(outputFilePath + "/" + outputFileName + ".gltf");
            File parentFile = outputFile.getParentFile();

            if (parentFile != null) parentFile.mkdirs();

            if (gltfWriteType == GltfWriteType.BINARY) gltfModelWriter.writeBinary(gltfModel, outputFile);
            else if (gltfWriteType == GltfWriteType.EMBEDDED) gltfModelWriter.writeEmbedded(gltfModel, outputFile);
            else gltfModelWriter.write(gltfModel, outputFile);

            long finishTime = System.nanoTime();
            long duration = finishTime - startTime;
            long seconds = TimeUnit.SECONDS.convert(duration, TimeUnit.NANOSECONDS);
            System.out.println(seconds + " seconds");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
