﻿<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Smart Editor&#8482;</title>
<link href="css/default.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jindo.min.js" charset="utf-8"></script>
<script type="text/javascript" src="js/Husky.SE_Basic.js" charset="utf-8"></script>
<script type="text/javascript" src="js/SE_CustomPlugins.js" charset="utf-8"></script>
</head>
<body>
<div id="smart_editor" class="smart_editor">
	<div id="smart_content"> <a href="#husky_iframe" class="skip">&raquo; 편집 도구모음 건너뛰기</a>
		<div class="tool">
			<ul class="type">
				<li class="husky_seditor_ui_fontName">
					<select class="husky_seditor_ui_fontName_select">
						<option value="">글꼴</option>
						<option value="dotum" style="font-family:Dotum">돋움</option>
						<option value="gulim" style="font-family:Gulim">굴림</option>
						<option value="batang" style="font-family:Batang">바탕</option>
						<option value="arial" style="font-family:Arial">Arial</option>
						<option value="arial black" style="font-family:'Arial Black'">Arial Black</option>
						<option value="tahoma" style="font-family:Tahoma">Tahoma</option>
						<option value="verdana" style="font-family:Verdana">Verdana</option>
						<option value="sans-serif" style="font-family:Sans-serif">Sans-serif</option>
						<option value="serif" style="font-family:Serif">Serif</option>
						<option value="monospace" style="font-family:Monospace">Monospace</option>
						<option value="cursive" style="font-family:Cursive">Cursive</option>
						<option value="fantasy" style="font-family:Fantasy">Fantasy</option>
					</select>
				</li>
				<li class="husky_seditor_ui_fontSize">
					<select class="husky_seditor_ui_fontSize_select">
						<option value="">크기</option>
						<option value="9px" style="font-size:9px">9px</option>
						<option value="10px" style="font-size:10px">10px</option>
						<option value="11px" style="font-size:11px">11px</option>
						<option value="12px" style="font-size:12px">12px</option>
						<option value="13px" style="font-size:13px">13px</option>
						<option value="14px" style="font-size:14px">14px</option>
						<option value="16px" style="font-size:16px">16px</option>
						<option value="18px" style="font-size:18px">18px</option>
						<option value="24px" style="font-size:24px">24px</option>
						<option value="32px" style="font-size:32px">32px</option>
					</select>
				</li>
				<li class="husky_seditor_ui_lineHeight">
					<select class="husky_seditor_ui_lineHeight_select">
						<option value="">줄간격</option>
						<option value="1">100%</option>
						<option value="1.2">120%</option>
						<option value="1.4">140%</option>
						<option value="1.6">160%</option>
						<option value="1.8">180%</option>
						<option value="2">200%</option>
					</select>
				</li>
			</ul>
			<ul class="style">
				<li class="bold husky_seditor_ui_bold">
					<button type="button" title="굵은글꼴[Ctrl+B]"><span>굵은글꼴</span></button>
				</li>
				<li class="underline husky_seditor_ui_underline">
					<button type="button" title="밑줄[Ctrl+U]"><span>밑줄</span></button>
				</li>
				<li class="italic husky_seditor_ui_italic">
					<button type="button" title="기울임글꼴[Ctrl+I]"><span>기울임글꼴</span></button>
				</li>
				<li class="del husky_seditor_ui_lineThrough">
					<button type="button" title="취소선[Ctrl+D]"><span>취소선</span></button>
				</li>
				<li class="fcolor husky_seditor_ui_fontColor">
					<button type="button" title="글자색"><span>글자색</span></button>
					<!-- 팔레트 레이어 -->
					<div class="layer husky_seditor_fontcolor_layer" style="display:none;">
						<ul class="palette husky_seditor_color_palette">
							<li><button type="button" title="#ff0000" style="background:#ff0000"><span>#ff0000</span></button></li>
							<li><button type="button" title="#ff6c00" style="background:#ff6c00"><span>#ff6c00</span></button></li>
							<li><button type="button" title="#ffaa00" style="background:#ffaa00"><span>#ffaa00</span></button></li>
							<li><button type="button" title="#ffef00" style="background:#ffef00"><span>#ffef00</span></button></li>
							<li><button type="button" title="#a6cf00" style="background:#a6cf00"><span>#a6cf00</span></button></li>
							<li><button type="button" title="#009e25" style="background:#009e25"><span>#009e25</span></button></li>
							<li><button type="button" title="#00b0a2" style="background:#00b0a2"><span>#00b0a2</span></button></li>
							<li><button type="button" title="#0075c8" style="background:#0075c8"><span>#0075c8</span></button></li>
							<li><button type="button" title="#3a32c3" style="background:#3a32c3"><span>#3a32c3</span></button></li>
							<li><button type="button" title="#7820b9" style="background:#7820b9"><span>#7820b9</span></button></li>
							<li><button type="button" title="#ef007c" style="background:#ef007c"><span>#ef007c</span></button></li>
							<li><button type="button" title="#000000" style="background:#000000"><span>#000000</span></button></li>
							<li><button type="button" title="#252525" style="background:#252525"><span>#252525</span></button></li>
							<li><button type="button" title="#464646" style="background:#464646"><span>#464646</span></button></li>
							<li><button type="button" title="#636363" style="background:#636363"><span>#636363</span></button></li>
							<li><button type="button" title="#7d7d7d" style="background:#7d7d7d"><span>#7d7d7d</span></button></li>
							<li><button type="button" title="#9a9a9a" style="background:#9a9a9a"><span>#9a9a9a</span></button></li>
							<li><button type="button" title="#ffe8e8" style="background:#ffe8e8"><span>#ffe8e8</span></button></li>
							<li><button type="button" title="#f7e2d2" style="background:#f7e2d2"><span>#f7e2d2</span></button></li>
							<li><button type="button" title="#f5eddc" style="background:#f5eddc"><span>#f5eddc</span></button></li>
							<li><button type="button" title="#f5f4e0" style="background:#f5f4e0"><span>#f5f4e0</span></button></li>
							<li><button type="button" title="#edf2c2" style="background:#edf2c2"><span>#edf2c2</span></button></li>
							<li><button type="button" title="#def7e5" style="background:#def7e5"><span>#def7e5</span></button></li>
							<li><button type="button" title="#d9eeec" style="background:#d9eeec"><span>#d9eeec</span></button></li>
							<li><button type="button" title="#c9e0f0" style="background:#c9e0f0"><span>#c9e0f0</span></button></li>
							<li><button type="button" title="#d6d4eb" style="background:#d6d4eb"><span>#d6d4eb</span></button></li>
							<li><button type="button" title="#e7dbed" style="background:#e7dbed"><span>#e7dbed</span></button></li>
							<li><button type="button" title="#f1e2ea" style="background:#f1e2ea"><span>#f1e2ea</span></button></li>
							<li><button type="button" title="#acacac" style="background:#acacac"><span>#acacac</span></button></li>
							<li><button type="button" title="#c2c2c2" style="background:#c2c2c2"><span>#c2c2c2</span></button></li>
							<li><button type="button" title="#cccccc" style="background:#cccccc"><span>#cccccc</span></button></li>
							<li><button type="button" title="#e1e1e1" style="background:#e1e1e1"><span>#e1e1e1</span></button></li>
							<li><button type="button" title="#ebebeb" style="background:#ebebeb"><span>#ebebeb</span></button></li>
							<li><button type="button" title="#ffffff" style="background:#ffffff"><span>#ffffff</span></button></li>
							<li><button type="button" title="#e97d81" style="background:#e97d81"><span>#e97d81</span></button></li>
							<li><button type="button" title="#e19b73" style="background:#e19b73"><span>#e19b73</span></button></li>
							<li><button type="button" title="#d1b274" style="background:#d1b274"><span>#d1b274</span></button></li>
							<li><button type="button" title="#cfcca2" style="background:#cfcca2"><span>#cfcca2</span></button></li>
							<li><button type="button" title="#cfcca2" style="background:#cfcca2"><span>#cfcca2</span></button></li>
							<li><button type="button" title="#61b977" style="background:#61b977"><span>#61b977</span></button></li>
							<li><button type="button" title="#53aea8" style="background:#53aea8"><span>#53aea8</span></button></li>
							<li><button type="button" title="#518fbb" style="background:#518fbb"><span>#518fbb</span></button></li>
							<li><button type="button" title="#6a65bb" style="background:#6a65bb"><span>#6a65bb</span></button></li>
							<li><button type="button" title="#9a54ce" style="background:#9a54ce"><span>#9a54ce</span></button></li>
							<li><button type="button" title="#e573ae" style="background:#e573ae"><span>#e573ae</span></button></li>
							<li><button type="button" title="#5a504b" style="background:#5a504b"><span>#5a504b</span></button></li>
							<li><button type="button" title="#767b86" style="background:#767b86"><span>#767b86</span></button></li>
							<li><button type="button" title="#00ffff" style="background:#00ffff"><span>#00ffff</span></button></li>
							<li><button type="button" title="#00ff00" style="background:#00ff00"><span>#00ff00</span></button></li>
							<li><button type="button" title="#a0f000" style="background:#a0f000"><span>#a0f000</span></button></li>
							<li><button type="button" title="#ffff00" style="background:#ffff00"><span>#ffff00</span></button></li>
							<li><button type="button" title="#951015" style="background:#951015"><span>#951015</span></button></li>
							<li><button type="button" title="#6e391a" style="background:#6e391a"><span>#6e391a</span></button></li>
							<li><button type="button" title="#785c25" style="background:#785c25"><span>#785c25</span></button></li>
							<li><button type="button" title="#5f5b25" style="background:#5f5b25"><span>#5f5b25</span></button></li>
							<li><button type="button" title="#4c511f" style="background:#4c511f"><span>#4c511f</span></button></li>
							<li><button type="button" title="#1c4827" style="background:#1c4827"><span>#1c4827</span></button></li>
							<li><button type="button" title="#0d514c" style="background:#0d514c"><span>#0d514c</span></button></li>
							<li><button type="button" title="#1b496a" style="background:#1b496a"><span>#1b496a</span></button></li>
							<li><button type="button" title="#2b285f" style="background:#2b285f"><span>#2b285f</span></button></li>
							<li><button type="button" title="#45245b" style="background:#45245b"><span>#45245b</span></button></li>
							<li><button type="button" title="#721947" style="background:#721947"><span>#721947</span></button></li>
							<li><button type="button" title="#352e2c" style="background:#352e2c"><span>#352e2c</span></button></li>
							<li><button type="button" title="#3c3f45" style="background:#3c3f45"><span>#3c3f45</span></button></li>
							<li><button type="button" title="#00aaff" style="background:#00aaff"><span>#00aaff</span></button></li>
							<li><button type="button" title="#0000ff" style="background:#0000ff"><span>#0000ff</span></button></li>
							<li><button type="button" title="#a800ff" style="background:#a800ff"><span>#a800ff</span></button></li>
							<li><button type="button" title="#ff00ff" style="background:#ff00ff"><span>#ff00ff</span></button></li>
						</ul>
					</div>
					<!-- /팔레트 레이어 -->
				</li>
				<li class="bcolor husky_seditor_ui_bgColor">
					<button type="button" title="배경색"><span>배경색</span></button>
					<!-- 배경색 + 팔레트 레이어 -->
					<div class="layer husky_seditor_bgcolor_layer" style="display:none;">
						<ul class="background">
							<li><button type="button" title="#000000" style="background:#000000; color:#ffffff"><span>가나다</span></button></li>
							<li><button type="button" title="#9334d8" style="background:#9334d8; color:#ffffff"><span>가나다</span></button></li>
							<li><button type="button" title="#ff0000" style="background:#ff0000; color:#ffffff"><span>가나다</span></button></li>
							<li><button type="button" title="#333333" style="background:#333333; color:#ffff00"><span>가나다</span></button></li>
							<li><button type="button" title="#0000ff" style="background:#0000ff; color:#ffffff"><span>가나다</span></button></li>
							<li><button type="button" title="#ff6600" style="background:#ff6600; color:#ffffff"><span>가나다</span></button></li>
							<li><button type="button" title="#8e8e8e" style="background:#8e8e8e; color:#ffffff"><span>가나다</span></button></li>
							<li><button type="button" title="#009999" style="background:#009999; color:#ffffff"><span>가나다</span></button></li>
							<li><button type="button" title="#ffa700" style="background:#ffa700; color:#ffffff"><span>가나다</span></button></li>
							<li><button type="button" title="#ffdaed" style="background:#ffdaed; color:#000000"><span>가나다</span></button></li>
							<li><button type="button" title="#e4ff75" style="background:#e4ff75; color:#000000"><span>가나다</span></button></li>
							<li><button type="button" title="#cc9900" style="background:#cc9900; color:#ffffff"><span>가나다</span></button></li>
							<li><button type="button" title="#99dcff" style="background:#99dcff; color:#000000"><span>가나다</span></button></li>
							<li><button type="button" title="#a6ff4d" style="background:#a6ff4d; color:#000000"><span>가나다</span></button></li>
							<li><button type="button" title="#ffffff" style="background:#ffffff; color:#000000"><span>가나다</span></button></li>
						</ul>
					</div>
					<!-- /배경색 + 팔레트 레이어 -->
				</li>
				<li class="sup husky_seditor_ui_superscript">
					<button type="button" title="윗첨자"><span>윗첨자</span></button>
				</li>
				<li class="sub husky_seditor_ui_subscript">
					<button type="button" title="아래첨자"><span>아래첨자</span></button>
				</li>
			</ul>
			<ul class="paragraph">
				<li class="left husky_seditor_ui_justifyleft">
					<button type="button" title="왼쪽정렬"><span>왼쪽정렬</span></button>
				</li>
				<li class="center husky_seditor_ui_justifycenter">
					<button type="button" title="가운데정렬"><span>가운데정렬</span></button>
				</li>
				<li class="right husky_seditor_ui_justifyright">
					<button type="button" title="오른쪽정렬"><span>오른쪽정렬</span></button>
				</li>
				<li class="justify husky_seditor_ui_justifyfull">
					<button type="button" title="양쪽정렬"><span>양쪽정렬</span></button>
				</li>
				<li class="ol husky_seditor_ui_orderedlist">
					<button type="button" title="순차목록"><span>순차목록</span></button>
				</li>
				<li class="ul husky_seditor_ui_unorderedlist">
					<button type="button" title="비순차목록"><span>비순차목록</span></button>
				</li>
				<li class="outdent husky_seditor_ui_outdent">
					<button type="button" title="내어쓰기[Shift+Tab]"><span>내어쓰기</span></button>
				</li>
				<li class="indent husky_seditor_ui_indent">
					<button type="button" title="들여쓰기[Tab]"><span>들여쓰기</span></button>
				</li>
			</ul>
			<ul class="extra">
				<li class="blockquote husky_seditor_ui_quote">
					<button type="button" title="인용"><span>인용</span></button>
					<!-- 인용 레이어 -->
					<div class="layer husky_seditor_blockquote_layer" style="display:none">
						<ul>
							<li class="q1"><button type="button"><span>왼쪽 실선</span></button></li>
							<li class="q2"><button type="button"><span>인용 부호</span></button></li>
							<li class="q3"><button type="button"><span>실선</span></button></li>
							<li class="q4"><button type="button"><span>실선 + 배경</span></button></li>
							<li class="q5"><button type="button"><span>굵은 실선</span></button></li>
							<li class="q6"><button type="button"><span>점선</span></button></li>
							<li class="q7"><button type="button"><span>점선 + 배경</span></button></li>
							<li class="q8"><button type="button"><span>적용 취소</span></button></li>
						</ul>
					</div>
					<!-- /인용 레이어 -->
				</li>
				<li class="imgupload husky_seditor_ui_imgupload">
				  <button type="button" title="이미지삽입"><span>이미지삽입</span></button>
				  <!-- 이미지삽입 레이어 -->
				  <div class="layer husky_seditor_imgupload_layer" style="display:none;">
					<div class="pic_content1">
					  <iframe src="" id="imgUploadFrame" frameborder="0" border="0" marginwidth="0" marginheight="0" scrolling="no" width="236" height="220"></iframe>
					</div>
				  </div>
				  <!-- /이미지삽입 레이어 -->
				</li>
				<li class="url husky_seditor_ui_hyperlink">
					<button type="button" title="링크"><span>URL</span></button>
					<!-- URL 레이어 -->
					<div class="layer husky_seditor_hyperlink_layer" style="display:none;">
						<fieldset>
							<h3>하이퍼링크</h3>
							<input name="" class="link" type="text" value="http://" title="URL" />
							<p><input name="" id="target" type="checkbox" value="" /><label for="target">새창으로</label></p>
						</fieldset>
						<div class="btn_area">
							<button type="button" class="confirm" title="확인"><span>확인</span></button>
							<button type="button" class="cancel" title="취소"><span>취소</span></button>
						</div>
					</div>
					<!-- /URL 레이어 -->
				</li>
				<li class="table husky_seditor_ui_table">
					<button type="button" title="표"><span>표</span></button>
					<!-- 표 레이어 -->
					<div class="layer husky_seditor_table_layer" style="display:none;">
						<fieldset class="num">
							<h3>칸 수 지정</h3>
							<dl>
								<dt>
									<label for="row">행</label>
								</dt>
								<dd>
									<input id="row" name="" type="text" maxlength="2" value="4" />
									<button type="button" class="add"><span>1행추가</span></button>
									<button type="button" class="del"><span>1행삭제</span></button>
								</dd>
								<dt>
									<label for="col">열</label>
								</dt>
								<dd>
									<input id="col" name="" type="text" maxlength="2" value="4" />
									<button type="button" class="add"><span>1행추가</span></button>
									<button type="button" class="del"><span>1행삭제</span></button>
								</dd>
							</dl>
							<table border="1">
								<tr>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
								</tr>
								<tr>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
									<td>&nbsp;</td>
								</tr>
							</table>
						</fieldset>
						<fieldset class="color">
							<h3>표 속성 지정</h3>
							<dl>
								<dt>
									<label for="table_border_width">테두리 굵기</label>
								</dt>
								<dd>
									<input id="table_border_width" name="" type="text" maxlength="2" value="1" />
									<button type="button" class="add"><span>1px 더하기</span></button>
									<button type="button" class="del"><span>1px 빼기</span></button>
								</dd>
								<dt>
									<label for="table_border_color">테두리 색</label>
								</dt>
								<dd>
									<span class="preview_palette"><button type="button" style="background:#cccccc;">색상찾기</button></span>
									<input id="table_border_color" name="" type="text" maxlength="7" value="#CCCCCC" />
									<button type="button" class="find_palette"><span>색상찾기</span></button>
								</dd>
								<dt>
									<label for="table_bg_color">표 배경색</label>
								</dt>
								<dd>
									<span class="preview_palette"><button type="button" style="background:#000000;">색상찾기</button></span>
									<input id="table_bg_color" name="" type="text" maxlength="7" value="#000000" />
									<button type="button" class="find_palette"><span>색상찾기</span></button>
								</dd>
							</dl>
						</fieldset>
						<div class="btn_area">
							<button type="button" class="confirm" title="확인"><span>확인</span></button>
							<button type="button" class="cancel" title="취소"><span>취소</span></button>
						</div>
					</div>
					<!-- /표 레이어 -->
				</li>
				<li class="character husky_seditor_ui_sCharacter">
					<button type="button" title="특수문자"><span>특수문자</span></button>
					<!-- 특수문자 레이어 -->
					<div class="layer husky_seditor_sCharacter_layer" style="display:none">
						<h3>특수문자 삽입</h3>
						<button type="button" class="close" title="특수문자 레이어 닫기"><span>특수문자 레이어 닫기</span></button>
						<ul class="nav">
							<li><a href="#character1" class="on">일반기호</a></li>
							<li><a href="#character2">숫자와 단위</a></li>
							<li><a href="#character3">원,괄호</a></li>
							<li><a href="#character4">한글</a></li>
							<li><a href="#character5">그리스,라틴어</a></li>
							<li><a href="#character6">일본어</a></li>
						</ul>
						<ul style="display: block;" id="character1" class="list"></ul>
						<ul style="display: none;" id="character2" class="list"></ul>
						<ul style="display: none;" id="character3" class="list"></ul>
						<ul style="display: none;" id="character4" class="list"></ul>
						<ul style="display: none;" id="character5" class="list"></ul>
						<ul style="display: none;" id="character6" class="list"></ul>
						<p>
							<label for="preview">선택한 기호</label>
							<input id="preview" name="" type="text" />
							<button type="button" title="확인"><span>확인</span></button>
						</p>
						<button type="button" class="close" title="특수문자 레이어 닫기"><span>특수문자 레이어 닫기</span></button>
					</div>
					<!-- /특수문자 레이어 -->
				</li>
				<li class="find husky_seditor_ui_findAndReplace" style="display:none">
					<button type="button" title="찾기"><span>찾기</span></button>
					<!-- 찾기 바꾸기 레이어 -->
					<div class="layer find husky_seditor_findAndReplace_layer" style="display:none">
					<!-- class="layer find" | class="layer replace"-->
						<h3>찾기/바꾸기</h3>
						<button type="button" class="close" title="찾기/바꾸기 레이어 닫기"><span>찾기/바꾸기 레이어 닫기</span></button>
						<div class="menu_tab">
							<ul class="layer_tab">
								<li class="tab1"><a href="#find" onclick="return false">찾기</a></li>
								<li class="tab2"><a href="#replace" onclick="return false">바꾸기</a></li>
							</ul>
						</div>
						<div class="container">
							<div class="bx" id="find">
								<fieldset>
									<label for="keyword1">찾을단어</label>
									<input id="keyword1" name="" type="text" />
								</fieldset>
								<span class="cap"></span> </div>
							<div class="bx" id="replace">
								<fieldset>
									<label for="keyword2">찾을단어</label>
									<input id="keyword2" name="" type="text" />
									<br />
									<label for="keyword3">바꿀단어</label>
									<input id="keyword3" name="" type="text" />
								</fieldset>
							</div>
						</div>
						<div class="btn_area">
							<button type="button" class="find_next" title="다음찾기"><span>다음찾기</span></button>
							<button type="button" class="replace" title="바꾸기"><span>바꾸기</span></button>
							<button type="button" class="replace_all" title="모두바꾸기"><span>모두바꾸기</span></button>
							<button type="button" class="cancel" title="취소"><span>취소</span></button>
						</div>
						<button type="button" class="close" title="찾기/바꾸기 레이어 닫기"><span>찾기/바꾸기 레이어 닫기</span></button>
					</div>
					<!-- /찾기 바꾸기 레이어 -->
				</li>
			</ul>
			<button type="button" class="html husky_seditor_mode_toggle_button" title="HTML 편집기"><span>HTML</span></button>
		</div>
		<hr />
		<!-- 입력 -->
		<div class="input_area husky_seditor_editing_area_container">
			<iframe src="se_blank.html" id="husky_iframe" name="husky_iframe" class="input_wysiwyg" frameborder="0" scrolling="yes" title="리치 에디터 - 편집기에서 빠져 나오시려면 ESC키를 누르세요" style="display:block;"></iframe>
			<textarea name="" rows="10" cols="20" title="HTML 편집 모드" class="input_syntax" style="display:none;">&lt;p&gt;&lt;/p&gt;</textarea>
			<textarea name="" cols="" rows="" class="blind" title="데이터 전송을 위한 숨은 콘트롤" style="display:none;"></textarea>
		</div>
		<!-- /입력 -->
		<button type="button" class="input_control husky_seditor_editingArea_verticalResizer" title="입력창 크기 조절"><span>입력창 크기 조절</span></button>
	</div>
</div>
</body>
</html>
