sql server

----------------



sqlserver 分页
	SELECT TOP 10 * FROM unit_info
	WHERE
		unit_id NOT IN (
			SELECT
				TOP 5 unit_id
			FROM
				unit_info
			ORDER BY
				unit_id
		)
	ORDER BY
		unit_id

---------------------------------------------------------