package com.ycs.base.utils;

import java.beans.Introspector;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.reflect.Method;
import java.text.Collator;
import java.util.Arrays;
import java.util.Collection;
import java.util.Locale;
import java.util.WeakHashMap;

public class JavaUtil {

	public static final char NL = '\n';
	public static final char CR = '\r';
	public static final String LS = System.getProperty("line.separator", (new Character('\n')).toString());
	static final String[] keywords = new String[]{"abstract", "assert", "boolean", "break", "byte", "case", "catch",
			"char", "class", "const", "continue", "default", "do", "double", "else", "extends", "false", "final",
			"finally", "float", "for", "goto", "if", "implements", "import", "instanceof", "int", "interface", "long",
			"native", "new", "null", "package", "private", "protected", "public", "return", "short", "static",
			"strictfp", "super", "switch", "synchronized", "this", "throw", "throws", "transient", "true", "try",
			"void", "volatile", "while"};
	static final Collator englishCollator;
	static final char keywordPrefix = '_';
	private static WeakHashMap enumMap;
	private static boolean checkForAttachmentSupport;
	private static boolean attachmentSupportEnabled;

	public static Class getWrapperClass(Class primitive) {
		return primitive == Integer.TYPE
				? Integer.class
				: (primitive == Short.TYPE
						? Short.class
						: (primitive == Boolean.TYPE
								? Boolean.class
								: (primitive == Byte.TYPE
										? Byte.class
										: (primitive == Long.TYPE
												? Long.class
												: (primitive == Double.TYPE
														? Double.class
														: (primitive == Float.TYPE
																? Float.class
																: (primitive == Character.TYPE
																		? Character.class
																		: null)))))));
	}

	public static String getWrapper(String primitive) {
		return primitive.equals("int")
				? "Integer"
				: (primitive.equals("short")
						? "Short"
						: (primitive.equals("boolean")
								? "Boolean"
								: (primitive.equals("byte")
										? "Byte"
										: (primitive.equals("long")
												? "Long"
												: (primitive.equals("double")
														? "Double"
														: (primitive.equals("float")
																? "Float"
																: (primitive.equals("char") ? "Character" : null)))))));
	}

	public static Class getPrimitiveClass(Class wrapper) {
		return wrapper == Integer.class
				? Integer.TYPE
				: (wrapper == Short.class
						? Short.TYPE
						: (wrapper == Boolean.class
								? Boolean.TYPE
								: (wrapper == Byte.class
										? Byte.TYPE
										: (wrapper == Long.class
												? Long.TYPE
												: (wrapper == Double.class
														? Double.TYPE
														: (wrapper == Float.class
																? Float.TYPE
																: (wrapper == Character.class
																		? Character.TYPE
																		: null)))))));
	}

	public static Class getPrimitiveClassFromName(String primitive) {
		return primitive
				.equals("int")
						? Integer.TYPE
						: (primitive
								.equals("short")
										? Short.TYPE
										: (primitive
												.equals("boolean")
														? Boolean.TYPE
														: (primitive.equals("byte")
																? Byte.TYPE
																: (primitive.equals("long")
																		? Long.TYPE
																		: (primitive.equals("double")
																				? Double.TYPE
																				: (primitive.equals("float")
																						? Float.TYPE
																						: (primitive.equals("char")
																								? Character.TYPE
																								: null)))))));
	}

	public static boolean isJavaId(String id) {
		if (id != null && !id.equals("") && !isJavaKeyword(id)) {
			if (!Character.isJavaIdentifierStart(id.charAt(0))) {
				return false;
			} else {
				for (int i = 1; i < id.length(); ++i) {
					if (!Character.isJavaIdentifierPart(id.charAt(i))) {
						return false;
					}
				}

				return true;
			}
		} else {
			return false;
		}
	}

	public static boolean isJavaKeyword(String keyword) {
		return Arrays.binarySearch(keywords, keyword, englishCollator) >= 0;
	}

	public static String makeNonJavaKeyword(String keyword) {
		return '_' + keyword;
	}

	public static String getLoadableClassName(String text) {
		if (text != null && text.indexOf("[") >= 0 && text.charAt(0) != 91) {
			String className = text.substring(0, text.indexOf("["));
			if (className.equals("byte")) {
				className = "B";
			} else if (className.equals("char")) {
				className = "C";
			} else if (className.equals("double")) {
				className = "D";
			} else if (className.equals("float")) {
				className = "F";
			} else if (className.equals("int")) {
				className = "I";
			} else if (className.equals("long")) {
				className = "J";
			} else if (className.equals("short")) {
				className = "S";
			} else if (className.equals("boolean")) {
				className = "Z";
			} else {
				className = "L" + className + ";";
			}

			for (int i = text.indexOf("]"); i > 0; i = text.indexOf("]", i + 1)) {
				className = "[" + className;
			}

			return className;
		} else {
			return text;
		}
	}

	public static String getTextClassName(String text) {
		if (text != null && text.indexOf("[") == 0) {
			String className = "";

			int index;
			for (index = 0; index < text.length() && text.charAt(index) == 91; className = className + "[]") {
				++index;
			}

			if (index < text.length()) {
				if (text.charAt(index) == 66) {
					className = "byte" + className;
				} else if (text.charAt(index) == 67) {
					className = "char" + className;
				} else if (text.charAt(index) == 68) {
					className = "double" + className;
				} else if (text.charAt(index) == 70) {
					className = "float" + className;
				} else if (text.charAt(index) == 73) {
					className = "int" + className;
				} else if (text.charAt(index) == 74) {
					className = "long" + className;
				} else if (text.charAt(index) == 83) {
					className = "short" + className;
				} else if (text.charAt(index) == 90) {
					className = "boolean" + className;
				} else {
					className = text.substring(index + 1, text.indexOf(";")) + className;
				}
			}

			return className;
		} else {
			return text;
		}
	}

	public static String xmlNameToJava(String name) {
		if (name != null && !name.equals("")) {
			char[] nameArray = name.toCharArray();
			int nameLen = name.length();
			StringBuffer result = new StringBuffer(nameLen);
			boolean wordStart = false;

			int i;
			for (i = 0; i < nameLen
					&& (isPunctuation(nameArray[i]) || !Character.isJavaIdentifierStart(nameArray[i])); ++i) {
				;
			}

			if (i < nameLen) {
				result.append(nameArray[i]);
				wordStart = !Character.isLetter(nameArray[i]) && nameArray[i] != "_".charAt(0);
			} else if (Character.isJavaIdentifierPart(nameArray[0])) {
				result.append("_" + nameArray[0]);
			} else {
				result.append("_" + nameArray.length);
			}

			++i;

			for (; i < nameLen; ++i) {
				char newName = nameArray[i];
				if (!isPunctuation(newName) && Character.isJavaIdentifierPart(newName)) {
					if (wordStart && Character.isLowerCase(newName)) {
						result.append(Character.toUpperCase(newName));
					} else {
						result.append(newName);
					}

					wordStart = !Character.isLetter(newName) && newName != "_".charAt(0);
				} else {
					wordStart = true;
				}
			}

			String arg6 = result.toString();
			if (Character.isUpperCase(arg6.charAt(0))) {
				arg6 = Introspector.decapitalize(arg6);
			}

			if (isJavaKeyword(arg6)) {
				arg6 = makeNonJavaKeyword(arg6);
			}

			return arg6;
		} else {
			return name;
		}
	}

	private static boolean isPunctuation(char c) {
		return 45 == c || 46 == c || 58 == c || 183 == c || 903 == c || 1757 == c || 1758 == c;
	}

	public static final String replace(String name, String oldT, String newT) {
		if (name == null) {
			return "";
		} else {
			StringBuffer sb = new StringBuffer(name.length() * 2);
			int len = oldT.length();

			try {
				int start = 0;

				for (int i = name.indexOf(oldT, start); i >= 0; i = name.indexOf(oldT, start)) {
					sb.append(name.substring(start, i));
					sb.append(newT);
					start = i + len;
				}

				if (start < name.length()) {
					sb.append(name.substring(start));
				}
			} catch (NullPointerException arg6) {
				;
			}

			return new String(sb);
		}
	}

	public static boolean isEnumClass(Class cls) {
		Boolean b = (Boolean) enumMap.get(cls);
		if (b == null) {
			b = isEnumClassSub(cls) ? Boolean.TRUE : Boolean.FALSE;
			WeakHashMap arg1 = enumMap;
			synchronized (enumMap) {
				enumMap.put(cls, b);
			}
		}

		return b.booleanValue();
	}

	private static boolean isEnumClassSub(Class cls) {
		try {
			Method[] e = cls.getMethods();
			Method getValueMethod = null;
			Object fromValueMethod = null;
			Method setValueMethod = null;
			Method fromStringMethod = null;

			for (int i = 0; i < e.length; ++i) {
				String name = e[i].getName();
				if (name.equals("getValue") && e[i].getParameterTypes().length == 0) {
					getValueMethod = e[i];
				} else if (name.equals("fromString")) {
					Class[] params = e[i].getParameterTypes();
					if (params.length == 1 && params[0] == String.class) {
						fromStringMethod = e[i];
					}
				} else if (name.equals("fromValue") && e[i].getParameterTypes().length == 1) {
					Method arg9999 = e[i];
				} else if (name.equals("setValue") && e[i].getParameterTypes().length == 1) {
					setValueMethod = e[i];
				}
			}

			if (null != getValueMethod && null != fromStringMethod) {
				if (null != setValueMethod && setValueMethod.getParameterTypes().length == 1
						&& getValueMethod.getReturnType() == setValueMethod.getParameterTypes()[0]) {
					return false;
				} else {
					return true;
				}
			} else {
				return false;
			}
		} catch (SecurityException arg8) {
			return false;
		}
	}

	public static String stackToString(Throwable e) {
		StringWriter sw = new StringWriter(1024);
		PrintWriter pw = new PrintWriter(sw);
		e.printStackTrace(pw);
		pw.close();
		return sw.toString();
	}

	public static final boolean isTrue(String value) {
		return !isFalseExplicitly(value);
	}

	public static final boolean isTrueExplicitly(String value) {
		return value != null && (value.equalsIgnoreCase("true") || value.equals("1") || value.equalsIgnoreCase("yes"));
	}

	public static final boolean isTrueExplicitly(Object value, boolean defaultVal) {
		return value == null
				? defaultVal
				: (value instanceof Boolean
						? ((Boolean) value).booleanValue()
						: (value instanceof Integer
								? ((Integer) value).intValue() != 0
								: (value instanceof String ? isTrueExplicitly((String) value) : true)));
	}

	public static final boolean isTrueExplicitly(Object value) {
		return isTrueExplicitly(value, false);
	}

	public static final boolean isTrue(Object value, boolean defaultVal) {
		return !isFalseExplicitly(value, !defaultVal);
	}

	public static final boolean isTrue(Object value) {
		return isTrue(value, false);
	}

	public static final boolean isFalse(String value) {
		return isFalseExplicitly(value);
	}

	public static final boolean isFalseExplicitly(String value) {
		return value == null || value.equalsIgnoreCase("false") || value.equals("0") || value.equalsIgnoreCase("no");
	}

	public static final boolean isFalseExplicitly(Object value, boolean defaultVal) {
		return value == null
				? defaultVal
				: (value instanceof Boolean
						? !((Boolean) value).booleanValue()
						: (value instanceof Integer
								? ((Integer) value).intValue() == 0
								: (value instanceof String ? isFalseExplicitly((String) value) : false)));
	}

	public static final boolean isFalseExplicitly(Object value) {
		return isFalseExplicitly(value, true);
	}

	public static final boolean isFalse(Object value, boolean defaultVal) {
		return isFalseExplicitly(value, defaultVal);
	}

	public static final boolean isFalse(Object value) {
		return isFalse(value, true);
	}

	public static String mimeToJava(String mime) {
		return !"image/gif".equals(mime) && !"image/jpeg".equals(mime)
				? ("text/plain".equals(mime)
						? "java.lang.String"
						: (!"text/xml".equals(mime) && !"application/xml".equals(mime)
								? (!"application/octet-stream".equals(mime) && !"application/octetstream".equals(mime)
										? (mime != null && mime.startsWith("multipart/")
												? "javax.mail.internet.MimeMultipart"
												: "javax.activation.DataHandler")
										: "org.apache.axis.attachments.OctetStream")
								: "javax.xml.transform.Source"))
				: "java.awt.Image";
	}

	public static String getUniqueValue(Collection values, String initValue) {
		if (!values.contains(initValue)) {
			return initValue;
		} else {
			StringBuffer unqVal = new StringBuffer(initValue);

			int beg;
			for (beg = unqVal.length(); Character.isDigit(unqVal.charAt(beg - 1)); --beg) {
				;
			}

			if (beg == unqVal.length()) {
				unqVal.append('1');
			}

			int end;
			int cur = end = unqVal.length() - 1;

			while (true) {
				while (values.contains(unqVal.toString())) {
					if (unqVal.charAt(cur) < 57) {
						unqVal.setCharAt(cur, (char) (unqVal.charAt(cur) + 1));
					} else {
						while (cur-- > beg) {
							if (unqVal.charAt(cur) < 57) {
								unqVal.setCharAt(cur, (char) (unqVal.charAt(cur) + 1));
								break;
							}
						}

						if (cur < beg) {
							++cur;
							unqVal.insert(cur, '1');
							++end;
						}

						while (cur < end) {
							++cur;
							unqVal.setCharAt(cur, '0');
						}
					}
				}

				return unqVal.toString();
			}
		}
	}

	static {
		englishCollator = Collator.getInstance(Locale.ENGLISH);
		enumMap = new WeakHashMap();
		checkForAttachmentSupport = true;
		attachmentSupportEnabled = false;
	}

}
