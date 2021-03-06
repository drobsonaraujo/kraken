package org.krakenapps.pcap.decoder.smb.comparser;
import org.krakenapps.pcap.decoder.smb.SmbSession;
import org.krakenapps.pcap.decoder.smb.request.ReadRequest;
import org.krakenapps.pcap.decoder.smb.response.ReadResponse;
import org.krakenapps.pcap.decoder.smb.structure.SmbData;
import org.krakenapps.pcap.decoder.smb.structure.SmbHeader;
import org.krakenapps.pcap.util.Buffer;
import org.krakenapps.pcap.util.ByteOrderConverter;


public class ReadParser implements SmbDataParser{

	@Override
	public SmbData parseRequest(SmbHeader h , Buffer b , SmbSession session) {
		ReadRequest data = new ReadRequest();
		data.setWordCount(b.get());
		data.setFid(ByteOrderConverter.swap(b.getShort()));
		data.setCountOfBytesToRead(ByteOrderConverter.swap(b.getShort()));
		data.setReadOffSetInBytes(ByteOrderConverter.swap(b.getInt()));
		data.setEstimateOfRemainingBytesToBeRead(ByteOrderConverter.swap(b.getShort()));
		data.setByteCount(ByteOrderConverter.swap(b.getShort()));
		return data;
	}
	@Override
	public SmbData parseResponse(SmbHeader h , Buffer b ,SmbSession session) {
		ReadResponse data = new ReadResponse();
		byte []reserved  =  new byte[8];
		byte []bytes;
		data.setWordCount(b.get());
		if(data.getWordCount() == 0x05){
			data.setCountOfBytesReturned(ByteOrderConverter.swap(b.getShort()));
			b.gets(reserved);
			data.setReserved(reserved);
		}
		else{
			data.setMalformed(true);
		}
			
		data.setByteCount(ByteOrderConverter.swap(b.getShort()));
		if(b.readableBytes() != data.getByteCount()){
			data.setMalformed(true);
			return data;
		}
		else if(b.readableBytes() ==0){
			data.setMalformed(true);
			return data;
		}
		data.setBufferFormat(b.get());
		data.setCountOfBytesRead(ByteOrderConverter.swap(b.getShort()));
		bytes = new byte[data.getCountOfBytesRead()]; 
		b.gets(bytes);
		data.setBytes(bytes); // it must file content
		return data;
	}
	
}
