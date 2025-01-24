export const metadata = {
  title: 'Sistema Simples',
  description: 'Frontend do Sistema Simples em Next.js',
};

export default function RootLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  return (
    <html lang="pt-BR">
      <body>{children}</body>
    </html>
  );
}
