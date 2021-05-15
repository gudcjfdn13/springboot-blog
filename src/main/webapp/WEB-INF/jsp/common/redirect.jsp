<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<script>
	var msg = '${msg}';
	msg = msg.trim()
	if (msg.length > 0) {
		alert(msg);
	}

	var historyBack = 'true' == '${historyBack}'
	if (historyBack) {
		history.back();
	}

	var replaceUri = '${replaceUri}'
	replaceUri = replaceUri.trim();
	if (replaceUri) {
		location.replace(replaceUri);
	}
</script>