import express, { Request, Response } from 'express';
import cors from 'cors';

const app = express();
app.use(cors());

interface LastFridayQuery {
  year?: string;
  month?: string;
}

function lastFriday(year: number, month: number): number {
  const lastDay = new Date(year, month, 0);
  const daysBack = (lastDay.getDay() - 5 + 7) % 7;
  return lastDay.getDate() - daysBack;
}

app.get('/last-friday', (req: Request<{}, {}, {}, LastFridayQuery>, res: Response) => {
  const year = req.query.year ? parseInt(req.query.year, 10) : NaN;
  const month = req.query.month ? parseInt(req.query.month, 10) : NaN;

  if (Number.isNaN(year) || Number.isNaN(month) || month < 1 || month > 12) {
    res.status(400).json({ error: 'year and month (1-12) are required' });
    return;
  }

  const day = lastFriday(year, month);
  res.json({ year, month, lastFriday: day });
});

app.get('/health', (_req: Request, res: Response) => {
  res.json({ status: 'ok' });
});

const PORT = process.env.PORT || 5002;
app.listen(PORT, () => {
  console.log(`TypeScript Last Friday API running on port ${PORT}`);
});
