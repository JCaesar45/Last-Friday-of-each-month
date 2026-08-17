from datetime import date, timedelta
from flask import Flask, jsonify, request
from flask_cors import CORS

app = Flask(__name__)
CORS(app)


def last_friday(year: int, month: int) -> int:
    """Return the day-of-month of the last Friday for a given year and month."""
    last_day = date(year, month, 1).replace(day=1) + timedelta(days=31)
    last_day = last_day.replace(day=1) - timedelta(days=1)
    offset = (last_day.weekday() - 4) % 7
    return (last_day - timedelta(days=offset)).day


@app.route('/last-friday', methods=['GET'])
def last_friday_endpoint():
    year = request.args.get('year', type=int)
    month = request.args.get('month', type=int)

    if year is None or month is None or not (1 <= month <= 12):
        return jsonify({'error': 'year and month (1-12) are required'}), 400

    try:
        day = last_friday(year, month)
        return jsonify({
            'year': year,
            'month': month,
            'lastFriday': day
        })
    except ValueError as exc:
        return jsonify({'error': str(exc)}), 400


@app.route('/health', methods=['GET'])
def health():
    return jsonify({'status': 'ok'})


if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5001, debug=True)
