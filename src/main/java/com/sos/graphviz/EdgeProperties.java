package com.sos.graphviz;
import com.sos.graphviz.enums.ArrowType;
import com.sos.graphviz.enums.DirType;
import com.sos.graphviz.enums.PortPos;
import com.sos.graphviz.enums.SVGColor;
import com.sos.graphviz.properties.GraphvizEnumProperty;
import com.sos.graphviz.properties.GraphvizProperty;

/**
 * The properties of an edge.
 *
 * <p>
 *     The properties follows the valid attributes for Graphviz (@see <a href="http://www.graphviz.org/doc/info/attrs.html">http://www.graphviz.org/doc/info/attrs.html</a>)
 * </p>
 *
 * <p>To add an additinal attribute to this class:</p>
 * <ul>
 *     <li>Define a private class property.</li>
 *     <li>Expand the getContent() method for this property.</li>
 *     <li>Generate a getter an a setter method for this attribute.</li>
 * </ul>
 */
public class EdgeProperties extends GraphvizObject implements IGraphvizObject {
	private static final String		constEpilog	= "]";
	private static final String		constProlog	= "[";
	private final GraphvizProperty	arrowHead	= new GraphvizEnumProperty("arrowhead");
	private final GraphvizProperty	arrowSize	= new GraphvizProperty("arrowsize");
	private final GraphvizProperty	arrowTail	= new GraphvizEnumProperty("arrowtail");
	private final GraphvizProperty	color		= new GraphvizProperty("color");
	private final GraphvizProperty	comment		= new GraphvizProperty("comment");
	private final GraphvizProperty	constraint	= new GraphvizProperty("constraint");
	private final GraphvizProperty	decorate	= new GraphvizProperty("decorate");
	private final GraphvizProperty	dir			= new GraphvizEnumProperty("dir");
	private final GraphvizProperty	fontColor	= new GraphvizEnumProperty("fontcolor");
	private final GraphvizProperty	fontName	= new GraphvizProperty("fontname");
	private final GraphvizProperty	fontSize	= new GraphvizProperty("fontsize");
	private final GraphvizProperty	headURL		= new GraphvizProperty("headURL");
	private final GraphvizProperty	headClip	= new GraphvizProperty("headclip");
	private final GraphvizProperty	headPort	= new GraphvizProperty("headport");
	//TODO implements missing attributes followed after 'headport' - see http://www.graphviz.org/pub/scm/graphviz2/doc/info/attrs.html
	private final GraphvizProperty	headLabel	= new GraphvizProperty("headlabel");
	private final GraphvizProperty	id			= new GraphvizProperty("id");
	private final GraphvizProperty	label		= new GraphvizProperty("label");
	private final GraphvizProperty	lhead		= new GraphvizProperty("lhead");
	private final GraphvizProperty	ltail		= new GraphvizProperty("ltail");
	private final GraphvizProperty	samehead	= new GraphvizProperty("samehead");
	private final GraphvizProperty	sametail	= new GraphvizProperty("sametail");
	private final GraphvizProperty	tailLabel	= new GraphvizProperty("taillabel");
	private final GraphvizProperty	tailPort	= new GraphvizProperty("tailport");
	private final GraphvizProperty	url			= new GraphvizProperty("URL");
	private final GraphvizProperty	weight		= new GraphvizProperty("weight");

	public EdgeProperties() {
		super(constProlog, constEpilog);
	}

	@Override public String getContent() {
		StringBuilder sb = new StringBuilder();
		sb.append(arrowSize.getContent());
		sb.append(arrowHead.getContent());
		sb.append(arrowTail.getContent());
		sb.append(color.getContent());
		sb.append(comment.getContent());
		sb.append(constraint.getContent());
		sb.append(decorate.getContent());
		sb.append(dir.getContent());
		sb.append(fontColor.getContent());
		sb.append(fontName.getContent());
		sb.append(fontSize.getContent());
		sb.append(headClip.getContent());
		sb.append(headLabel.getContent());
		sb.append(headPort.getContent());
		sb.append(headURL.getContent());
		sb.append(id.getContent());
		sb.append(label.getContent());
		sb.append(lhead.getContent());
		sb.append(ltail.getContent());
		sb.append(samehead.getContent());
		sb.append(sametail.getContent());
		sb.append(tailLabel.getContent());
		sb.append(tailPort.getContent());
		sb.append(url.getContent());
		sb.append(weight.getContent());
		return sb.toString();
	}

	@Override public GraphvizObject getProperties() {
		return this;
	}

	public ArrowType getArrowHead() {
		return (ArrowType) arrowHead.getValue();
	}

	public double getArrowSize() {
		return (Double) arrowSize.getValue();
	}

	public ArrowType getArrowTail() {
		return (ArrowType) arrowTail.getValue();
	}

	public SVGColor getColor() {
		return (SVGColor) color.getValue();
	}

	public String getComment() {
		return (String) comment.getValue();
	}

	public boolean getConstraint() {
		return (Boolean) constraint.getValue();
	}

	public boolean getDecorate() {
		return (Boolean) decorate.getValue();
	}

	public DirType getDir() {
		return (DirType) dir.getValue();
	}

	public SVGColor getFontColor() {
		return (SVGColor) fontColor.getValue();
	}

	public String getFontName() {
		return (String) fontName.getValue();
	}

	public double getFontSize() {
		return (Double) fontSize.getValue();
	}

	public String getHeadLabel() {
		return (String) headLabel.getValue();
	}

	public String getLabel() {
		return (String) label.getValue();
	}

	public String getTailLabel() {
		return (String) tailLabel.getValue();
	}

	public String getUrl() {
		return (String) url.getValue();
	}

	public EdgeProperties setArrowHead(ArrowType arrowHead) {
		this.arrowHead.setValue(arrowHead);
		return this;
	}

	public EdgeProperties setArrowSize(double arrowSize) {
		this.arrowSize.setValue(arrowSize);
		return this;
	}

	public EdgeProperties setArrowTail(ArrowType arrowTail) {
		this.arrowTail.setValue(arrowTail);
		return this;
	}

	public EdgeProperties setColor(SVGColor color) {
		this.color.setValue(color);
		return this;
	}

	public EdgeProperties setComment(String comment) {
		this.comment.setValue(comment);
		return this;
	}

	public EdgeProperties setConstraint(boolean constraint) {
		this.constraint.setValue(constraint);
		return this;
	}

	public EdgeProperties setDecorate(boolean decorate) {
		this.decorate.setValue(decorate);
		return this;
	}

	public EdgeProperties setDir(DirType dir) {
		this.dir.setValue(dir);
		return this;
	}

	public EdgeProperties setFontColor(SVGColor fontColor) {
		this.fontColor.setValue(fontColor);
		return this;
	}

	public EdgeProperties setFontName(String fontName) {
		this.fontName.setValue(fontName);
		return this;
	}

	public EdgeProperties setFontSize(double fontSize) {
		this.fontSize.setValue(fontSize);
		return this;
	}

	public EdgeProperties setHeadLabel(String headLabel) {
		this.headLabel.setValue(headLabel);
		return this;
	}

	public EdgeProperties setLabel(String label) {
		this.label.setValue(label);
		return this;
	}

	public EdgeProperties setTailLabel(String tailLabel) {
		this.tailLabel.setValue(tailLabel);
		return this;
	}

	public EdgeProperties setUrl(String url) {
		this.url.setValue(url);
		return this;
	}

	public String getHeadURL() {
		return (String) headURL.getValue();
	}

	public boolean getHeadClip() {
		return (Boolean) headClip.getValue();
	}

	public PortPos getHeadPort() {
		return (PortPos) headPort.getValue();
	}

	public PortPos getTailPort() {
		return (PortPos) tailPort.getValue();
	}

	public EdgeProperties setHeadURL(String headURL) {
		this.headURL.setValue(headURL);
		return this;
	}

	public EdgeProperties setHeadClip(boolean headClip) {
		this.headClip.setValue(headClip);
		return this;
	}

	public EdgeProperties setHeadPort(PortPos headPort) {
		this.headPort.setValue(headPort);
		return this;
	}

	public EdgeProperties setTailPort(PortPos tailPort) {
		this.tailPort.setValue(tailPort);
		return this;
	}

	public double getWeight() {
		return (Double) weight.getValue();
	}

	public EdgeProperties setWeight(double weight) {
		this.weight.setValue(weight);
		return this;
	}

	public String getSamehead() {
		return (String) samehead.getValue();
	}

	public EdgeProperties setSamehead(String samehead) {
		this.samehead.setValue(samehead);
		return this;
	}

	public String getSametail() {
		return (String) sametail.getValue();
	}

	public EdgeProperties setSametail(String sametail) {
		this.sametail.setValue(sametail);
		return this;
	}

	public String getLhead() {
		return (String) lhead.getValue();
	}

	public EdgeProperties setLhead(String lhead) {
		this.lhead.setValue(lhead);
		return this;
	}

	public String getLtail() {
		return (String) ltail.getValue();
	}

	public EdgeProperties setLtail(String ltail) {
		this.ltail.setValue(ltail);
		return this;
	}

	public String getId() {
		return (String) id.getValue();
	}

	public EdgeProperties setId(String id) {
		this.id.setValue(id);
		return this;
	}
}
