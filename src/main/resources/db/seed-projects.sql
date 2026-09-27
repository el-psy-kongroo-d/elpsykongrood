INSERT INTO projects (slug, title, description, kind, website_url, portfolio_url, preview_image, display_order)
VALUES (
    'demo-project',
    '개발 기록 사이트',
    E'개발 기록을 검색하고 읽을 수 있는 웹사이트입니다.\n글 조회와 댓글 작성, 프로젝트 소개, PDF 미리보기를 제공합니다.',
    '샘플 프로젝트',
    '/',
    '/documents/projects/demo-project/portfolio',
    '/images/projects/demo-project/preview.svg',
    0
)
ON CONFLICT (slug) DO NOTHING;
