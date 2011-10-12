<%@ page language="java" isELIgnored="false" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:directive.include file="/WEB-INF/sitemesh-decorators/include.jsp"/>

							<tr align="center" bgcolor="F7F7F7">
								<td><input type="text" class="ins_point" style="width:50px" value="{.POINT}" tid="{.TID}" /></td>
								<td>아이콘
									<select class="ins_op_icon" tid="{.TID}">
									</select>
									, 강조
									<input type="checkbox" class="ins_op_strong" tid="{.TID}" value="1"{? .OP_STRONG == "1"} checked="checked"{/} />
									, 색상
									<input type="text" class="ins_op_color" tid="{.TID}" value="{.OP_COLOR}" />
									, 이태릭
									<input type="checkbox" class="ins_op_em" tid="{.TID}" value="1"{? .OP_EM == "1"} checked="checked"{/} /></td>
								<td><span class='button bull btn_mod_point_opt' tid="{.TID}"><a>수정</a></span>
									<span class='button bull btn_del_point_opt' tid="{.TID}"><a>삭제</a></span></td>
							</tr>
