CREATE VIEW v_replyTBL
AS
SELECT *

FROM reply

WHERE reply_blind = 1
;
