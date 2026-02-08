INSERT INTO plant (id, name)
VALUES
('PLANT_01', 'TataSteel Jamshedpur');

INSERT INTO production_line (id, plant_id, name, status)
VALUES
('LINE_1', 'PLANT_01', 'Pipe Line 1', 'RUNNING'),
('LINE_2', 'PLANT_01', 'Pipe Line 2', 'RUNNING');

INSERT INTO machine (id, line_id, name, type)
VALUES
('MC001', 'LINE_1', 'High Frequency Welding Unit', 'WELDER'),
('MC002', 'LINE_2', 'Pipe Forming Rolls', 'FORMER');

INSERT INTO shift (id, start_time, end_time)
VALUES
('SHIFT_A', '2025-09-01 00:00:00', '2025-09-01 23:59:00'),
('SHIFT_B', '2026-01-02 10:00:00', '2026-01-02 12:00:00');

INSERT INTO product (id, name, grade, standard)
VALUES
('PIPE_40MM', 'Steel Pipe 40mm', 'A36', 'ASTM');

INSERT INTO production_run
(id, line_id, product_id, shift_id, start_time, end_time, planned_qty, actual_qty)
VALUES
('RUN_1009', 'LINE_1', 'PIPE_40MM', 'SHIFT_A',
 '2025-09-01 06:00:00', '2025-09-01 08:00:00', 1200, 980),

('RUN_1010', 'LINE_2', 'PIPE_40MM', 'SHIFT_B',
 '2026-01-02 10:00:00', '2026-01-02 12:00:00', 1000, 80);

INSERT INTO downtime_event
(id, run_id, line_id, machine_id, reason, category, start_time, end_time, duration)
VALUES
('DT_201', 'RUN_1009', 'LINE_1', 'MC001',
 'Welding arc instability', 'MECHANICAL',
 '2025-09-01 06:45:00', '2025-09-01 07:00:00', 900),

('DT_202', 'RUN_1010', 'LINE_2', 'MC002',
 'Material jam in forming rolls', 'PROCESS',
 '2026-01-02 10:45:00', '2026-01-02 12:00:00', 4500);

INSERT INTO quality_event
(id, run_id, defect_type, quantity, machine_id)
VALUES
('1', 'RUN_1009', 'Crack', 45, 'MC001'),
('2', 'RUN_1010', 'Uneven', 10, 'MC002');

