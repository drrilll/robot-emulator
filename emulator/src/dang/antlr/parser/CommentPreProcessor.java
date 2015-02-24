package dang.antlr.parser;

/**
 * Strips off comments. Only single line comments for now,
 * but a full implementation is forthcoming. Some of them have
 * proven to be problematic for the indent and dedent preprocessor
 * @author darrylhill
 *
 */
public class CommentPreProcessor {

	public String process(String code){
		StringBuilder build = new StringBuilder(code);
		//strip out the '\r'
		int pos;
		while(true){
			pos = build.lastIndexOf("\r");
			if (pos == -1)break;
			build.deleteCharAt(pos);
		}
		int currentPos = 0;
		int start, end;
		while(true){
			start = build.indexOf("'", currentPos);
			if (start == -1) break;
			end = build.indexOf("\n",start);
			build.delete(start, end);
			currentPos = start;
		}
		return build.toString();
	}
}
