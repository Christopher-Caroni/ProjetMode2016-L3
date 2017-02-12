package ply.read.reader.body;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;

import ply.plyModel.elements.Face;
import ply.plyModel.elements.Point;
import ply.read.header.HeaderEntry;
import ply.read.header.property.FaceProperty;
import ply.read.header.property.Property;
import ply.read.reader.header.DataType;
import ply.read.reader.header.HeaderReader;
import ply.result.MethodResult;

public class BinaryReader extends BodyReader {

	private FileInputStream fileInputStream;
	private BufferedReader bufferedReader;
	private ByteArrayOutputStream byteBufferOutput;
	private ByteBuffer buffer;
	private ByteArrayInputStream byteBufferInput;

	public BinaryReader(HeaderReader headerReader, MethodResult readResult) throws IOException {
		super(headerReader, readResult);

		fileInputStream = new FileInputStream(headerReader.getFile());
		bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));
		byteBufferOutput = new ByteArrayOutputStream();

		parseBody();
	}

	@Override
	protected void parseBody() throws IOException {
		storeBinary();
		storeElements();
	}

	private void storeBinary() throws IOException {
		String line = "";
		int nChars = 0;
		boolean end = false;
		while ((line = bufferedReader.readLine()) != null) {
			nChars += line.length();
			if ("end_header".equals(line.toLowerCase())) {
				break;
			}
		}

		int nBytesRead = 0;
		byte[] readBuffer = new byte[1024];
		fileInputStream.skip(nChars);
		while ((nBytesRead = fileInputStream.read(readBuffer)) != -1) {
			byteBufferOutput.write(readBuffer);
		}
		byteBufferInput = new ByteArrayInputStream(byteBufferOutput.toByteArray());
		buffer = ByteBuffer.wrap(byteBufferOutput.toByteArray());
	}

	private void storeElements() throws IOException {
		for (HeaderEntry entry : headerReader.getHeaderList()) {
			if (entry.getName().equals("vertex")) {
				for (int vertexCount = 0; vertexCount < entry.getCount(); vertexCount++) {
					double[] coords = new double[3];
					for (int j = 0; j < coords.length; j++) {
						Property prop = entry.getPropertyList().get(j);
						coords[j] = readByte(prop.getType());
					}
					Point point = new Point(vertexCount, coords[0], coords[1], coords[2]);
					vertexList.add(point);
					vertexMap.put(vertexCount, point);
				}
			} else if (entry.getName().equals("face")) {
				FaceProperty faceProp = (FaceProperty) entry.getProperty("vertex_indices");
				for (int faceCount = 0; faceCount < entry.getCount(); faceCount++) {
					double nPointsInFace = readByte(faceProp.getNumberOfEntriesType());
					Face face = new Face(faceCount);
					for (int i = 0; i < nPointsInFace; i++) {
						double pointIndex = readByte(faceProp.getEntryType());
						face.addPoint(vertexMap.get(pointIndex));
					}
					faceMap.put(faceCount, face);
					faceList.add(face);
				}
			}
		}
	}

	private double readByte(DataType dataType) throws IOException {
		switch (dataType) {
		case CHAR:
			ensureAvailable(1);
			return buffer.get();
		case UCHAR:
			ensureAvailable(1);
			return ((int) buffer.get()) & 0x000000FF;
		case SHORT:
			ensureAvailable(2);
			return buffer.getShort();
		case USHORT:
			ensureAvailable(2);
			return ((int) buffer.getShort()) & 0x0000FFFF;
		case INT:
			ensureAvailable(4);
			return buffer.getInt();
		case UINT:
			ensureAvailable(4);
			return ((long) buffer.getShort()) & 0x00000000FFFFFFFF;
		case FLOAT:
			ensureAvailable(4);
			return buffer.getFloat();
		case DOUBLE:
			ensureAvailable(8);
			return buffer.getDouble();
		default:
			throw new IOException("Cannot read bytes for unexpected data type");
		}
	}

	private boolean ensureAvailable(int nBytes) throws IOException {
		if (byteBufferInput.available() >= nBytes) {
			return true;
		}
		throw new IOException("Not enough bytes to be read");
	}
}
